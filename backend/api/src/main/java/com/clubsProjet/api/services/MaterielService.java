package com.clubsProjet.api.services;

import java.util.List;

import com.clubsProjet.api.DTO.MaterielDTO;
import com.clubsProjet.api.models.Materiel;

public interface MaterielService {

	List<Materiel> getAllMateriels();
	Materiel createMateriel(Materiel materiel);
	Materiel updateMateriel(int materielId,Materiel materiel);
	void deleteMateriel(int materielId);
}