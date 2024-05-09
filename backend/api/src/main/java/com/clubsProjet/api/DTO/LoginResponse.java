package com.clubsProjet.api.DTO;

import lombok.Data;

@Data
public class LoginResponse {

	private String jwt;
	private String role;
}
