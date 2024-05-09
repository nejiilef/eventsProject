package com.clubsProjet.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clubsProjet.api.DTO.MaterielDTO;
import com.clubsProjet.api.exceptions.MaterielNotFoundException;
import com.clubsProjet.api.models.Materiel;
import com.clubsProjet.api.repositories.MaterielRepository;

@Service
public class MaterielServiceImpl implements MaterielService{

	private MaterielRepository materielRepository;
	
	
	public MaterielServiceImpl(MaterielRepository materielRepository) {
		super();
		this.materielRepository = materielRepository;
	}

	@Override
	public List<Materiel> getAllMateriels() {
		// TODO Auto-generated method stub
		return this.materielRepository.findAll();
	}

	@Override
	public Materiel createMateriel(Materiel materiel) {
		// TODO Auto-generated method stub
		return this.materielRepository.save(materiel);
	}

	@Override
	public Materiel updateMateriel(int materielId, Materiel materiel) {
		// TODO Auto-generated method stub
		Materiel m=this.materielRepository.findById(materielId).orElseThrow(()->new MaterielNotFoundException("Materiel Inexistant !"));

		m.setLibelle(materiel.getLibelle());
		m.setReserve(materiel.isReserve());
		m.setId(materielId);
		m.setQuantite(materiel.getQuantite());
		return this.materielRepository.save(m);
	}

	@Override
	public void deleteMateriel(int materielId) {
		// TODO Auto-generated method stub
		Materiel m=this.materielRepository.findById(materielId).orElseThrow(()->new MaterielNotFoundException("Materiel Inexistant !"));
		this.materielRepository.delete(m);
	}

}
