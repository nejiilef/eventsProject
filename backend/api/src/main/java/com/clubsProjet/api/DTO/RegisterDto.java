package com.clubsProjet.api.DTO;



import lombok.Data;

@Data
public class RegisterDto {
	private String nom;
    private String prenom;
    private String username;
    private String password;
    private Integer telephone;
    private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}