package com.clubsProjet.api.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ClubDTO {

	 private int id;
	    private String nom;
	    private String desc;

	 
}
