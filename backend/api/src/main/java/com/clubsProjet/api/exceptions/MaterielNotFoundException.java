package com.clubsProjet.api.exceptions;

public class MaterielNotFoundException extends RuntimeException{

	 private static final long serialVerisionUID = 1;
	 public MaterielNotFoundException(String message) {
		 super(message);
	 }
}
