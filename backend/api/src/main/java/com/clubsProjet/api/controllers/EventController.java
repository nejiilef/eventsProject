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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.DTO.EventDTO;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.Event;
import com.clubsProjet.api.services.EventService;

@RestController
@RequestMapping("/api")
public class EventController {

	private EventService eventService;

	public EventController(EventService eventService) {
		super();
		this.eventService = eventService;
	}
	
	@GetMapping("/event")
	public List<Event> getAllClubs(){
		return this.eventService.getAllEvents();
	}
	@PostMapping("/event/{username}")
	public ResponseEntity<Event> createSalle(@RequestBody EventDTO event,@PathVariable("username") String username){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.eventService.createEvent(event, "ilefnj@gmail.com"));
	}
	@PutMapping("/event/{idEvent}")
	public ResponseEntity<Event> updateMateriel(@RequestBody EventDTO event,@PathVariable(value="idEvent") int idEvent){
		return ResponseEntity.status(HttpStatus.OK).body(this.eventService.updateEvent(idEvent, event));
	}
	@DeleteMapping("/event/{id}")
	public ResponseEntity<String> deleteMateriel(@PathVariable(value="id") int id){
		this.eventService.deleteEvent(id);
		return ResponseEntity.status(HttpStatus.OK).body("Event deleted successfully");
	}
}
