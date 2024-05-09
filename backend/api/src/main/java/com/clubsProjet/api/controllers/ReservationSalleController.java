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

import com.clubsProjet.api.exceptions.SalleNotFoundException;
import com.clubsProjet.api.models.ReservationSalle;
import com.clubsProjet.api.services.ReservationSalleService;

@RestController
@RequestMapping("/api")
public class ReservationSalleController {

	private ReservationSalleService reservationSalleService;

	public ReservationSalleController(ReservationSalleService reservationSalleService) {
		super();
		this.reservationSalleService = reservationSalleService;
	}
	@GetMapping("/reservation/salle")
	public List<ReservationSalle> getAllReservationSalle(){
		return this.reservationSalleService.getAllReservationSalle();
	}
	@GetMapping("/reservationAttente/salle")
	public List<ReservationSalle> getAllReservationSalleEnAttente(){
		return this.reservationSalleService.getAllReservationSalleEnAttente();
	}
	
	@PostMapping("/reservation/salle/{salleId}")
	public ResponseEntity<ReservationSalle> createReservationSalle(@PathVariable(value="salleId") int salleId,@RequestBody ReservationSalle reservationSalle){
		if(this.reservationSalleService.isTimeRangeReserved(salleId, reservationSalle)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationSalleService.createReservationSalle(salleId,reservationSalle));
	}else {
		throw new SalleNotFoundException("Salle deja reserve pendant cette periode");
	}
	}
	@PutMapping("/reservation/salle/{id}/{salleId}")
	public ResponseEntity<ReservationSalle> updateReservationSalle(@PathVariable(value="salleId") int salleId,@PathVariable(value="id") int id,@RequestBody ReservationSalle reservationSalle){
		return ResponseEntity.status(HttpStatus.OK).body(this.reservationSalleService.updateReservationSalle(salleId, id, reservationSalle));
	}
	@PutMapping("/reservation/{etatId}/salle/{id}")
	public ResponseEntity<ReservationSalle> updateReservationSalleEtat(@PathVariable(value="id") int resId,@PathVariable(value="etatId") int etatId){
		return ResponseEntity.status(HttpStatus.OK).body(this.reservationSalleService.updateEtatReservation(resId, etatId));
	}
	@DeleteMapping("/reservation/salle/{id}")
	public ResponseEntity<String> deleteReservationSalle(@PathVariable(value="id") int id){
		this.reservationSalleService.deleteReservationSalle(id);
		return ResponseEntity.status(HttpStatus.OK).body("Reservation Salle deleted successfully");
	}
}
