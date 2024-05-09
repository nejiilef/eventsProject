package com.clubsProjet.api.services;

import java.util.List;

import com.clubsProjet.api.models.ReservationSalle;

public interface ReservationSalleService {

	List<ReservationSalle> getAllReservationSalle();
	ReservationSalle createReservationSalle(int salleId,ReservationSalle reservation);
	ReservationSalle updateReservationSalle(int salleId,int ReservationId,ReservationSalle reservation);
	void deleteReservationSalle(int reservationId);
	boolean isTimeRangeReserved(int salleId,ReservationSalle r);
	List<ReservationSalle> getAllReservationSalleEnAttente();
	ReservationSalle updateEtatReservation(int resId,int etatId);
}
