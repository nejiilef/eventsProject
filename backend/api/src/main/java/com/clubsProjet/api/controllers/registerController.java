package com.clubsProjet.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clubsProjet.api.DTO.RegisterDto;
import com.clubsProjet.api.DTO.UserDTO;
import com.clubsProjet.api.models.UserEntity;
import com.clubsProjet.api.services.AuthService;




@RestController
@RequestMapping("/register")
public class registerController {

	private final AuthService authService;

	 @Autowired
	public registerController(AuthService authService) {
		this.authService = authService;
	}
	 @PostMapping
	    public ResponseEntity<?> RegisterUser(@RequestBody RegisterDto registerDto) {
	        UserEntity createdUser = authService.createUser(registerDto);
	        if (createdUser != null) {
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
	        }
	    }
	
	 @GetMapping("/user")
	 public List<UserDTO> getAllUsers(){
	     return this.authService.getAllUsers();
	 }

	 @PutMapping("/user/{id}")
		public ResponseEntity<UserDTO> updateUser(@PathVariable(value="id") int uId,@RequestBody RegisterDto userDTO){
			UserDTO u=this.authService.updateUser(uId, userDTO);
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}
	 @DeleteMapping("/user/{id}")
		public ResponseEntity<String> deleteProprietaire(@PathVariable(value="id") int userId){
			this.authService.deleteUser(userId);
			return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully");
			
		}
}
