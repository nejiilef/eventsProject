package com.clubsProjet.api.repositories;

import com.clubsProjet.api.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer> {
}
