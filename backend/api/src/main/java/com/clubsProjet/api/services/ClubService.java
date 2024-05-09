package com.clubsProjet.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.models.Club;

public interface ClubService {

	List<Club> getAllClubs();
	Club createClub(ClubDTO clubDTO,int chefId);
	Club updateClub(int ClubId,ClubDTO clubDTO,int chefId);
	void deleteClub(int clubId);
	Club findByChef_id(String username);
}
