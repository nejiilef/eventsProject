package com.clubsProjet.api.exceptions;

public class ReservationMaterielNotFoundException extends RuntimeException{

	 private static final long serialVerisionUID = 1;
	 public ReservationMaterielNotFoundException(String message) {
		 super(message);
	 }
}