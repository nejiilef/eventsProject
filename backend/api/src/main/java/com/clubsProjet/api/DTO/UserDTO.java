package com.clubsProjet.api.DTO;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private Integer telephone;
}
