package com.clubsProjet.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.Materiel;
import com.clubsProjet.api.models.Salle;
import com.clubsProjet.api.services.ClubService;

@RestController
@RequestMapping("/api")
public class ClubController {

	private ClubService clubService;

	public ClubController(ClubService clubService) {
		super();
		this.clubService = clubService;
	}
	
	@GetMapping("/club")
	public List<Club> getAllClubs(){
		return this.clubService.getAllClubs();
	}
	@GetMapping("/club/chef")
	public ResponseEntity<Club> getClubByChef(@RequestParam(value = "username") String username) {

	    return ResponseEntity.status(HttpStatus.OK).body(this.clubService.findByChef_id(username));
	}

	@PostMapping("/club/{id}")
	public ResponseEntity<Club> createSalle(@RequestBody ClubDTO club,@PathVariable(value="id") int chefId){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.clubService.createClub( club, chefId));
	}
	@PutMapping("/club/{idClub}/{idChef}")
	public ResponseEntity<Club> updateMateriel(@PathVariable(value="idClub") int idClub,@RequestBody ClubDTO club,@PathVariable(value="idChef") int idChef){
		return ResponseEntity.status(HttpStatus.OK).body(this.clubService.updateClub(idClub, club, idChef));
	}
	@DeleteMapping("/club/{id}")
	public ResponseEntity<String> deleteMateriel(@PathVariable(value="id") int id){
		this.clubService.deleteClub(id);
		return ResponseEntity.status(HttpStatus.OK).body("Club deleted successfully");
	}
	
}
