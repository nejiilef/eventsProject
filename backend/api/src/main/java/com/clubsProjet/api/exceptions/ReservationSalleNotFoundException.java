package com.clubsProjet.api.exceptions;

public class ReservationSalleNotFoundException extends RuntimeException{

	 private static final long serialVerisionUID = 1;
	 public ReservationSalleNotFoundException(String message) {
		 super(message);
	 }
}
