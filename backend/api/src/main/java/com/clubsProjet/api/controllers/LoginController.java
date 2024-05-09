package com.clubsProjet.api.controllers;


import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clubsProjet.api.DTO.LoginDto;
import com.clubsProjet.api.DTO.LoginResponse;
import com.clubsProjet.api.jwt.JwtUtil;
import com.clubsProjet.api.services.CustomUserDetailsService;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customerService;

    private final JwtUtil jwtUtil;


    @Autowired
    public LoginController(AuthenticationManager authenticationManager, CustomUserDetailsService customerService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
            return null;
        }
        final UserDetails userDetails = customerService.loadUserByUsername(loginDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        LoginResponse l=new LoginResponse();
        l.setJwt(jwt);
        if(userDetails.getAuthorities().toString().equals("[admin]")) {
        l.setRole("admin");}
        else if(userDetails.getAuthorities().toString().equals("[chef]")){
        	 l.setRole("chef");
        }else {
        	l.setRole("user");
        }
        logger.error("Un menu a déjà été ajouté pour la date du jour : {}", l.getRole());
        return l;
    }

}
