package com.clubsProjet.api.services;

import java.util.List;

import com.clubsProjet.api.DTO.RegisterDto;
import com.clubsProjet.api.DTO.UserDTO;
import com.clubsProjet.api.models.UserEntity;

public interface AuthService {
    UserEntity createUser(RegisterDto registerDto);

	List<UserDTO> getAllUsers();

	UserDTO updateUser(int uId, RegisterDto registerDto);

	void deleteUser(int uId);
}
