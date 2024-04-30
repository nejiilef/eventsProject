package com.clubsProjet.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clubsProjet.api.models.Role;



public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByNom(String nom);

}
