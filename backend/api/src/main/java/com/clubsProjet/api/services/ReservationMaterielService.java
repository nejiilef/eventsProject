package com.clubsProjet.api.services;

import java.util.List;

import com.clubsProjet.api.models.ReservationMateriel;

public interface ReservationMaterielService {

	List<ReservationMateriel> getAllReservationMateriel();
	ReservationMateriel createReservationMateriel(int materielId,ReservationMateriel reservation);
	ReservationMateriel updateReservationMateriel(int materielId,int ReservationId,ReservationMateriel reservation);
	void deleteReservationMateriel(int reservationId);
	boolean isTimeRangeReserved(int MaReservationMaterielId,ReservationMateriel r);
	List<ReservationMateriel> getAllReservationMaterielEnAttente();
	ReservationMateriel updateEtatReservation(int ReservationId,int etatId);
	
}
