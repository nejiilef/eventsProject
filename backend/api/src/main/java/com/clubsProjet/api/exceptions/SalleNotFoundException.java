package com.clubsProjet.api.exceptions;

public class SalleNotFoundException extends RuntimeException{
	 private static final long serialVerisionUID = 1;
	 public SalleNotFoundException(String message) {
		 super(message);
	 }
}
