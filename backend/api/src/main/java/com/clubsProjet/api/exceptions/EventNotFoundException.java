package com.clubsProjet.api.exceptions;

public class EventNotFoundException extends RuntimeException{

	 private static final long serialVerisionUID = 1;
	 public EventNotFoundException(String message) {
		 super(message);
	 }
}
