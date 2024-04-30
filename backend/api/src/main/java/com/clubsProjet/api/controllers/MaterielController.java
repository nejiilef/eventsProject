package com.clubsProjet.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clubsProjet.api.models.Materiel;
import com.clubsProjet.api.services.MaterielService;

@RestController
@RequestMapping("/api")
public class MaterielController {

	private MaterielService materielService;

	public MaterielController(MaterielService materielService) {
		this.materielService = materielService;
	}
	
	@GetMapping("/materiel")
	public List<Materiel> getAllMateriel(){
		return this.materielService.getAllMateriels();
	}
	
	@PostMapping("/materiel")
	public ResponseEntity<Materiel> createMateriel(@RequestBody Materiel materiel){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.materielService.createMateriel(materiel));
	}
	
	@PutMapping("/materiel/{id}")
	public ResponseEntity<Materiel> updateMateriel(@PathVariable(value="id") int id,@RequestBody Materiel materiel){
		return ResponseEntity.status(HttpStatus.OK).body(this.materielService.updateMateriel(id, materiel));
	}
	@DeleteMapping("/materiel/{id}")
	public ResponseEntity<String> deleteMateriel(@PathVariable(value="id") int id){
		this.materielService.deleteMateriel(id);
		return ResponseEntity.status(HttpStatus.OK).body("Materiel deleted successfully");
	}
}
