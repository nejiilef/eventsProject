package com.clubsProjet.api.services;

import com.clubsProjet.api.DTO.RegisterDto;
import com.clubsProjet.api.models.UserEntity;

public interface AuthService {
    UserEntity createUser(RegisterDto registerDto);
}
