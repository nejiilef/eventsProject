package com.clubsProjet.api.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clubsProjet.api.DTO.RegisterDto;
import com.clubsProjet.api.DTO.UserDTO;
import com.clubsProjet.api.exceptions.UserNotFoundException;
import com.clubsProjet.api.models.Role;
import com.clubsProjet.api.models.UserEntity;
import com.clubsProjet.api.repositories.RoleRepository;
import com.clubsProjet.api.repositories.UserRepository;



@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository=roleRepository;
    }


    @Override
    public UserEntity createUser(RegisterDto registerDto) {
        //Check if customer already exist
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        Role role = roleRepository.findByNom(registerDto.getRole()).get();
        user.setRole(role);
        user.setNom(registerDto.getNom());
        user.setPrenom(registerDto.getPrenom());
        user.setTelephone(registerDto.getTelephone());
        userRepository.save(user);
        return user;
    }
    @Override
   	public List<UserDTO> getAllUsers() {
   		// TODO Auto-generated method stub
   		return this.userRepository.findAll().stream().map(p -> mapToDto(p)).collect(Collectors.toList());
   	}
    private UserDTO mapToDto(UserEntity u) {
		 UserDTO uDto = new UserDTO();
	       uDto.setId(u.getId());
	        uDto.setNom(u.getNom());
	        uDto.setPrenom(u.getPrenom());
	        uDto.setTelephone(u.getTelephone());
	        uDto.setUsername(u.getUsername());
	        uDto.setPassword(u.getPassword());
	        return uDto;
	    }
    
   	public UserDTO updateUser(int uId, RegisterDto registerDto) {
   		UserEntity u=this.userRepository.findById(uId).orElseThrow(()->new UserNotFoundException("User NOT FOUND !"));
   		u.setNom(registerDto.getNom());;
   		u.setPrenom(registerDto.getPrenom());
   		u.setTelephone(registerDto.getTelephone());
   		u.setPassword(passwordEncoder.encode((registerDto.getPassword())));
   		u.setUsername(registerDto.getUsername());
       	UserEntity userUpdated=this.userRepository.save(u);
       	
   		return mapToDto(userUpdated);
   	}
    @Override
    public void deleteUser(int uId) {
    		
    		UserEntity u=this.userRepository.findById(uId).orElseThrow(()->new UserNotFoundException("USER NOT FOUND !"));
    		
    		this.userRepository.delete(u);
    	}
}
