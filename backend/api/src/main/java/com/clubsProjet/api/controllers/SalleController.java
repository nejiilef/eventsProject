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

import com.clubsProjet.api.DTO.SalleDTO;
import com.clubsProjet.api.models.Salle;
import com.clubsProjet.api.services.SalleService;

@RestController
@RequestMapping("/api")
public class SalleController {

	private SalleService salleService;

	public SalleController(SalleService salleService) {
		this.salleService = salleService;
	}
	
	@GetMapping("/salle")
	public List<Salle> getAllSalles(){
		return this.salleService.getAllSalles();
	}
	
	@PostMapping("/salle")
	public ResponseEntity<Salle> createSalle(@RequestBody Salle salle){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.salleService.createSalle(salle));
	}
	
	@PutMapping("/salle/{id}")
	public ResponseEntity<Salle> updateSalle(@PathVariable(value="id") int id,@RequestBody SalleDTO salle){
		return ResponseEntity.status(HttpStatus.OK).body(this.salleService.updateSalle(id, salle));
	}
	
	@DeleteMapping("/salle/{id}")
	public ResponseEntity<String> deleteSalle(@PathVariable(value="id") int id){
		this.salleService.deleteSalle(id);
		return ResponseEntity.status(HttpStatus.OK).body("Salle deleted successfully");
	}
	
}
