package com.clubsProjet.api.services;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.DTO.SalleDTO;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.Salle;

import java.util.List;

public interface ClubService {
    List<Club> getAllClubs();
    Club createClub(Club club , int chef_id);
    Club updateClub(int ClubId, ClubDTO club , int chef_id);
    void deleteClub(int ClubId);
}
