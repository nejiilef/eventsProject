package com.clubsProjet.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterielDTO {

	 private int id;
	    private String libelle;
	    private boolean reserve;
	    
	    
}
