package com.clubsProjet.api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clubsProjet.api.DTO.RegisterDto;
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
}
