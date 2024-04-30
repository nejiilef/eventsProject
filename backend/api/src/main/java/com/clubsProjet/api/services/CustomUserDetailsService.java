package com.clubsProjet.api.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clubsProjet.api.models.UserEntity;
import com.clubsProjet.api.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private UserRepository userRepository;
    

    public CustomUserDetailsService() {
		
	}

	@Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Collection<GrantedAuthority> c=new ArrayList<GrantedAuthority>();
        c.add(new SimpleGrantedAuthority(user.getRole().getNom()));
        return new User(user.getUsername(), user.getPassword(), c );
    }
 }

   