package com.clubsProjet.api.exceptions;

public class ClubNotFoundException extends RuntimeException{

	 private static final long serialVerisionUID = 1;
	 public ClubNotFoundException(String message) {
		 super(message);
	 }
}
