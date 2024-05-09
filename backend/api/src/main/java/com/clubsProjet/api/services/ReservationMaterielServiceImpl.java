package com.clubsProjet.api.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clubsProjet.api.exceptions.MaterielNotFoundException;
import com.clubsProjet.api.exceptions.ReservationMaterielNotFoundException;
import com.clubsProjet.api.exceptions.MaterielNotFoundException;
import com.clubsProjet.api.models.ReservationMateriel;
import com.clubsProjet.api.models.Materiel;
import com.clubsProjet.api.repositories.ReservationMaterielRepository;
import com.clubsProjet.api.repositories.EtatRepository;
import com.clubsProjet.api.repositories.MaterielRepository;

@Service
public class ReservationMaterielServiceImpl implements ReservationMaterielService{
	
	private ReservationMaterielRepository reservationMaterielRepository;
	private EtatRepository etatRepository;
	private MaterielRepository MaterielRepository;
	

	

	public ReservationMaterielServiceImpl(ReservationMaterielRepository reservationMaterielRepository,
			EtatRepository etatRepository, com.clubsProjet.api.repositories.MaterielRepository materielRepository) {
		super();
		this.reservationMaterielRepository = reservationMaterielRepository;
		this.etatRepository = etatRepository;
		MaterielRepository = materielRepository;
	}

	@Override
	public List<ReservationMateriel> getAllReservationMateriel() {
		// TODO Auto-generated method stub
		return this.reservationMaterielRepository.findAll();
	}

	@Override
	public ReservationMateriel createReservationMateriel(int MaterielId,ReservationMateriel reservation) {
		// TODO Auto-generated method stub
		Materiel s=this.MaterielRepository.findById(MaterielId).orElseThrow(()-> new MaterielNotFoundException("Materiel not found !"));
		reservation.setEtat(this.etatRepository.findById(3).orElseThrow(null));
		reservation.setMateriel(s);
		return this.reservationMaterielRepository.save(reservation);
	}

	@Override
	public ReservationMateriel updateReservationMateriel(int MaterielId,int reservationId, ReservationMateriel reservation) {
		ReservationMateriel r=this.reservationMaterielRepository.findById(reservationId).orElseThrow(()->new ReservationMaterielNotFoundException("Reservation de Materiel Inexistante !"));
		Materiel s=this.MaterielRepository.findById(MaterielId).orElseThrow(()-> new MaterielNotFoundException("Materiel not found !"));
		r.setMateriel(s);
        r.setLibelle(reservation.getLibelle());
		r.setJour(reservation.getJour());
		r.setHeureDebut(reservation.getHeureDebut());
		r.setHeureFin(reservation.getHeureFin());
		r.setEtat(reservation.getEtat());
		r.setId(reservationId);
		return this.reservationMaterielRepository.save(r);
	}

	@Override
	public void deleteReservationMateriel(int reservationId) {
		ReservationMateriel r=this.reservationMaterielRepository.findById(reservationId).orElseThrow(()->new ReservationMaterielNotFoundException("Reservation de Materiel Inexistante !"));
		this.reservationMaterielRepository.delete(r);
	}

	public boolean isTimeRangeReserved(int MaterielId,ReservationMateriel r) {
        // Check if there are any reservations that overlap with the specified time range
		Materiel Materiel=this.MaterielRepository.findById(MaterielId).orElseThrow(()-> new MaterielNotFoundException("Materiel not found !"));
		LocalTime heureDebut=r.getHeureDebut();
		LocalTime heureFin=r.getHeureFin();
		LocalDate jour=r.getJour();
        String libelle= r.getLibelle();
        List<ReservationMateriel> reservations = reservationMaterielRepository.findReservationsByTimeRange(heureDebut,heureFin,jour,Materiel);
        return reservations.isEmpty();
    }

	@Override
	public List<ReservationMateriel> getAllReservationMaterielEnAttente() {
		// TODO Auto-generated method stub
		List<ReservationMateriel> l=new ArrayList<>();
		for(ReservationMateriel r:this.getAllReservationMateriel()) {
			if(r.getEtat().getId()==3) {
				l.add(r);
			}
		}
		return l;
	}

	@Override
	public ReservationMateriel updateEtatReservation(int ReservationId, int etatId) {
		// TODO Auto-generated method stub
		ReservationMateriel r=this.reservationMaterielRepository.findById(ReservationId).orElseThrow(()->new ReservationMaterielNotFoundException("Reservation de Materiel Inexistante !"));
		r.setEtat(this.etatRepository.findById(etatId).orElseThrow(null));
		this.updateReservationMateriel(r.getMateriel().getId(), ReservationId, r);
		return r;
	}
}