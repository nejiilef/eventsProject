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

import com.clubsProjet.api.exceptions.MaterielNotFoundException;
import com.clubsProjet.api.models.ReservationMateriel;
import com.clubsProjet.api.services.ReservationMaterielService;

@RestController
@RequestMapping("/api")
public class ReservationMaterielController {

	private ReservationMaterielService reservationMaterielService;

	public ReservationMaterielController(ReservationMaterielService reservationMaterielService) {
		super();
		this.reservationMaterielService = reservationMaterielService;
	}
	@GetMapping("/reservation/materiel")
	public List<ReservationMateriel> getAllReservationMateriel(){
		return this.reservationMaterielService.getAllReservationMateriel();
	}
	@GetMapping("/reservationAttente/materiel")
	public List<ReservationMateriel> getAllReservationMaterielEnAttente(){
		return this.reservationMaterielService.getAllReservationMaterielEnAttente();
	}
	@PostMapping("/reservation/materiel/{materielId}")
	public ResponseEntity<ReservationMateriel> createReservationMateriel(@PathVariable(value="materielId") int materielId,@RequestBody ReservationMateriel reservationMateriel){
		if(this.reservationMaterielService.isTimeRangeReserved(materielId, reservationMateriel)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationMaterielService.createReservationMateriel(materielId,reservationMateriel));
	}else {
		throw new MaterielNotFoundException("Materiel deja reserve pendant cette periode");
	}
	}
	@PutMapping("/reservation/materiel/{id}/{materielId}")
	public ResponseEntity<ReservationMateriel> updateReservationMateriel(@PathVariable(value="materielId") int materielId,@PathVariable(value="id") int id,@RequestBody ReservationMateriel reservationMateriel){
		return ResponseEntity.status(HttpStatus.OK).body(this.reservationMaterielService.updateReservationMateriel(materielId, id, reservationMateriel));
	}
	@PutMapping("/reservation/{etatId}/materiel/{id}")
	public ResponseEntity<ReservationMateriel> updateReservationMaterielEtat(@PathVariable(value="etatId") int etatId,@PathVariable(value="id") int resid){
		return ResponseEntity.status(HttpStatus.OK).body(this.reservationMaterielService.updateEtatReservation(resid, etatId));
	}
	
	@DeleteMapping("/reservation/materiel/{id}")
	public ResponseEntity<String> deleteReservationMateriel(@PathVariable(value="id") int id){
		this.reservationMaterielService.deleteReservationMateriel(id);
		return ResponseEntity.status(HttpStatus.OK).body("Reservation Materiel deleted successfully");
	}
}