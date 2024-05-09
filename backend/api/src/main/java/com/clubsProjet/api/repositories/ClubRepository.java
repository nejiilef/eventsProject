package com.clubsProjet.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.UserEntity;


public interface ClubRepository extends JpaRepository<Club, Integer>{

	 Club findByChef(UserEntity user);
}
