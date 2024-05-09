package com.clubsProjet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clubsProjet.api.models.Event;


public interface EventRepository extends JpaRepository<Event, Integer>{

}
