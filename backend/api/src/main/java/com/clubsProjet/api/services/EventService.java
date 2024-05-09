package com.clubsProjet.api.services;

import java.util.List;

import com.clubsProjet.api.DTO.EventDTO;
import com.clubsProjet.api.models.Event;

public interface EventService {

	List<Event> getAllEvents();
	Event createEvent(EventDTO eventDTO,String username);
	Event updateEvent(int eventId,EventDTO eventDTO);
	void deleteEvent(int eventId);
}
