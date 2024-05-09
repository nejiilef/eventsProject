package com.clubsProjet.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.exceptions.ClubNotFoundException;
import com.clubsProjet.api.exceptions.UserNotFoundException;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.UserEntity;
import com.clubsProjet.api.repositories.ClubRepository;
import com.clubsProjet.api.repositories.UserRepository;


@Service
public class ClubServiceImpl implements ClubService{
	
	private ClubRepository clubRepository;
	
	private UserRepository userRepository;
	

	public ClubServiceImpl(ClubRepository clubRepository,UserRepository userRepository) {
		super();
		this.clubRepository = clubRepository;
		this.userRepository=userRepository;
		}

	@Override
	public List<Club> getAllClubs() {
		return this.clubRepository.findAll();
	}
	@Override
	public Club findByChef_id(String username){
		UserEntity chef=this.userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("USER NOT FOUND !"));
		
		return this.clubRepository.findByChef(chef);
	}

	@Override
	public Club createClub(ClubDTO clubDTO, int chefId) {
		// TODO Auto-generated method stub
		Club club=this.mapToEntity(clubDTO);
		UserEntity chef=this.userRepository.findById(chefId).orElseThrow(()->new UserNotFoundException("USER NOT FOUND !"));
		club.setChef(chef);
		this.clubRepository.save(club);
		return club;
	}
	public Club mapToEntity(ClubDTO clubDTO) {
		Club club=new Club();
		club.setId(clubDTO.getId());
		club.setNom(clubDTO.getNom());
		club.setDesc(clubDTO.getDesc());
		return club;
	}

	@Override
	public Club updateClub(int clubId, ClubDTO clubDTO, int chefId) {
		// TODO Auto-generated method stub
		Club club=this.clubRepository.findById(clubId).orElseThrow(()->new ClubNotFoundException("Club Not found !"));

		club.setNom(clubDTO.getNom());
		club.setDesc(clubDTO.getDesc());
		UserEntity chef=this.userRepository.findById(chefId).orElseThrow(()->new UserNotFoundException("USER NOT FOUND !"));
		club.setChef(chef);
		
		return club;
	}

	@Override
	public void deleteClub(int clubId) {
		// TODO Auto-generated method stub
		Club club=this.clubRepository.findById(clubId).orElseThrow(()->new ClubNotFoundException("Club Not found !"));
this.clubRepository.delete(club);
	}

}
