package com.clubsProjet.api.controllers;

import com.clubsProjet.api.DTO.ClubDTO;
import com.clubsProjet.api.models.Club;
import com.clubsProjet.api.models.Salle;
import com.clubsProjet.api.services.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/club")
    public List<Club> getAllClubs(){
        return this.clubService.getAllClubs();
    }

    @PostMapping("/club/{id_chef}")
    public ResponseEntity<Club> createClub(@RequestBody Club club , @PathVariable(value="id_chef") int id_chef){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.clubService.createClub(club , id_chef));

    }

    @PutMapping("/club/{id}/{id_chef}")
    public Club updateClub(@PathVariable(value="id") int id, @RequestBody ClubDTO club , @PathVariable(value="id_chef") int id_chef){
        return this.clubService.updateClub(id, club , id_chef);
    }

    @DeleteMapping("/club/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable(value="id") int id){
        this.clubService.deleteClub(id);
        return ResponseEntity.status(HttpStatus.OK).body("Club deleted successfully");
    }
}
