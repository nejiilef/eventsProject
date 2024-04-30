package com.clubsProjet.api.services;

import java.util.List;

import com.clubsProjet.api.DTO.SalleDTO;
import com.clubsProjet.api.models.Salle;

public interface SalleService {

	List<Salle> getAllSalles();
	Salle createSalle(Salle salle);
	Salle updateSalle(int salleId,SalleDTO salle);
	void deleteSalle(int salleId);
}
