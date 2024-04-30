package com.clubsProjet.api.services;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.UserEntity;
import com.clubsProjet.api.repositories.ClubRepository;
import com.clubsProjet.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClubServiceImpl implements ClubService{
    private ClubRepository clubRepository;
    private UserRepository UserRep;

    public ClubServiceImpl(ClubRepository clubRepository , UserRepository UserRep) {
        this.clubRepository = clubRepository;
        this.UserRep = UserRep;
    }

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    @Override
    public Club createClub(Club club , int chef_id){
        UserEntity chef = UserRep.findById(chef_id).orElseThrow(() -> new RuntimeException("User not found"));
        club.setChef_id(chef);
        return clubRepository.save(club);
    }

    @Override
    public Club updateClub(int ClubId, ClubDTO club , int chef_id) {
        Club clubToUpdate = clubRepository.findById(ClubId).orElseThrow(() -> new RuntimeException("Club not found"));
        clubToUpdate.setNom(club.getNom());
        clubToUpdate.setDescription(club.getDescription());
        clubToUpdate.setImage(club.getImage());
        UserEntity chef = UserRep.findById(chef_id).orElseThrow(() -> new RuntimeException("User not found"));
        clubToUpdate.setChef_id(chef) ;
        return clubRepository.save(clubToUpdate);
    }

    @Override
    public void deleteClub(int ClubId) {
        clubRepository.deleteById(ClubId);
    }
}
