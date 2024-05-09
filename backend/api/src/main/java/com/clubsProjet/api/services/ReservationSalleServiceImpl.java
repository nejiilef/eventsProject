package com.clubsProjet.api.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clubsProjet.api.exceptions.MaterielNotFoundException;
import com.clubsProjet.api.exceptions.ReservationSalleNotFoundException;
import com.clubsProjet.api.exceptions.SalleNotFoundException;
import com.clubsProjet.api.models.ReservationSalle;
import com.clubsProjet.api.models.Salle;
import com.clubsProjet.api.repositories.EtatRepository;
import com.clubsProjet.api.repositories.ReservationSalleRepository;
import com.clubsProjet.api.repositories.SalleRepository;

@Service
public class ReservationSalleServiceImpl implements ReservationSalleService{
	
	private ReservationSalleRepository reservationSalleRepository;

	private EtatRepository etatRepository;
	private SalleRepository salleRepository;
	

	

	public ReservationSalleServiceImpl(ReservationSalleRepository reservationSalleRepository,
			EtatRepository etatRepository, SalleRepository salleRepository) {
		super();
		this.reservationSalleRepository = reservationSalleRepository;
		this.etatRepository = etatRepository;
		this.salleRepository = salleRepository;
	}

	@Override
	public List<ReservationSalle> getAllReservationSalle() {
		// TODO Auto-generated method stub
		return this.reservationSalleRepository.findAll();
	}

	@Override
	public ReservationSalle createReservationSalle(int salleId,ReservationSalle reservation) {
		// TODO Auto-generated method stub
		Salle s=this.salleRepository.findById(salleId).orElseThrow(()-> new SalleNotFoundException("Salle not found !"));
		reservation.setEtat(this.etatRepository.findById(3).orElseThrow(null));
		reservation.setSalle(s);
		return this.reservationSalleRepository.save(reservation);
	}

	@Override
	public ReservationSalle updateReservationSalle(int salleId,int reservationId, ReservationSalle reservation) {
		ReservationSalle r=this.reservationSalleRepository.findById(reservationId).orElseThrow(()->new ReservationSalleNotFoundException("Reservation de salle Inexistante !"));
		Salle s=this.salleRepository.findById(salleId).orElseThrow(()-> new SalleNotFoundException("Salle not found !"));
		r.setSalle(s);
		r.setJour(reservation.getJour());
		r.setHeureDebut(reservation.getHeureDebut());
		r.setHeureFin(reservation.getHeureFin());
		r.setId(reservationId);
		r.setEtat(reservation.getEtat());
		return this.reservationSalleRepository.save(r);
	}

	@Override
	public void deleteReservationSalle(int reservationId) {
		ReservationSalle r=this.reservationSalleRepository.findById(reservationId).orElseThrow(()->new ReservationSalleNotFoundException("Reservation de salle Inexistante !"));
		this.reservationSalleRepository.delete(r);
	}

	public boolean isTimeRangeReserved(int salleId,ReservationSalle r) {
        // Check if there are any reservations that overlap with the specified time range
		Salle salle=this.salleRepository.findById(salleId).orElseThrow(()-> new SalleNotFoundException("Salle not found !"));
		LocalTime heureDebut=r.getHeureDebut();
		LocalTime heureFin=r.getHeureFin();
		LocalDate jour=r.getJour();
        List<ReservationSalle> reservations = reservationSalleRepository.findReservationsByTimeRange(heureDebut,heureFin,jour,salle);
        return reservations.isEmpty();
    }

	@Override
	public List<ReservationSalle> getAllReservationSalleEnAttente() {
		List<ReservationSalle> l=new ArrayList<>();
		for(ReservationSalle r:this.getAllReservationSalle()) {
			if(r.getEtat().getId()==3) {
				l.add(r);
			}
		}
		return l;
	}

	@Override
	public ReservationSalle updateEtatReservation(int resId, int etatId) {
		ReservationSalle r=this.reservationSalleRepository.findById(resId).orElseThrow(()->new ReservationSalleNotFoundException("Reservation de salle Inexistante !"));
		r.setEtat(this.etatRepository.findById(etatId).orElseThrow(null));
		this.updateReservationSalle(r.getSalle().getId(), resId, r);
		return r;
	}
	
}
