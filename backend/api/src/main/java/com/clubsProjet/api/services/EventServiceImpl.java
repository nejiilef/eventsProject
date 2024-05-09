package com.clubsProjet.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clubsProjet.api.DTO.EventDTO;
import com.clubsProjet.api.exceptions.ClubNotFoundException;
import com.clubsProjet.api.exceptions.EventNotFoundException;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.Event;
import com.clubsProjet.api.models.UserEntity;
import com.clubsProjet.api.repositories.ClubRepository;
import com.clubsProjet.api.repositories.EventRepository;
import com.clubsProjet.api.repositories.UserRepository;


@Service
public class EventServiceImpl implements EventService{
	
	private EventRepository eventRepository;

	private ClubRepository clubRepository;
	private UserRepository userRepository;
	
	
	

	public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository,
			UserRepository userRepository) {
		super();
		this.eventRepository = eventRepository;
		this.clubRepository = clubRepository;
		this.userRepository = userRepository;
	}


	@Override
	public List<Event> getAllEvents() {
		// TODO Auto-generated method stub
		return this.eventRepository.findAll();
	}

	
	public Event mapToEntity(EventDTO eventDTO) {
		Event event=new Event();
		event.setId(eventDTO.getId());
		event.setLibelle(eventDTO.getLibelle());
		event.setDescription(eventDTO.getDescription());
		return event;
	}

	@Override
	public Event createEvent(EventDTO eventDTO, String username) {
		Event e=this.mapToEntity(eventDTO);
		UserEntity u=this.userRepository.findByUsername(username).orElseThrow(()->new ClubNotFoundException("user Not found !"));
		Club club=this.clubRepository.findByChef(u);
		e.setClub(club);
		this.eventRepository.save(e);
		return this.eventRepository.save(e);
	}

	@Override
	public Event updateEvent(int eventId, EventDTO eventDTO) {
		// TODO Auto-generated method stub
		Event e=this.eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Event not found !"));
		
		e.setDescription(eventDTO.getDescription());
		e.setLibelle(eventDTO.getLibelle());
		return this.eventRepository.save(e);
	}

	@Override
	public void deleteEvent(int eventId) {
		// TODO Auto-generated method stub
		Event e=this.eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Event not found !"));
		this.eventRepository.delete(e);
		
	}

}
