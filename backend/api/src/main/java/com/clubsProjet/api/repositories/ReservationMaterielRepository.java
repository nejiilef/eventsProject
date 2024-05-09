package com.clubsProjet.api.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.clubsProjet.api.models.Materiel;
import com.clubsProjet.api.models.ReservationMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReservationMaterielRepository extends JpaRepository<ReservationMateriel, Integer>{


	@Query("SELECT r FROM ReservationMateriel r WHERE (((:heureDebut <= r.heureDebut AND :heureFin >= r.heureFin) or (:heureDebut <= r.heureDebut AND :heureFin >= r.heureDebut) or (:heureDebut <= r.heureFin AND :heureFin >= r.heureFin)) and :jour=r.jour and :materiel=r.materiel)")
	List<ReservationMateriel> findReservationsByTimeRange(@Param("heureDebut") LocalTime heureDebut, @Param("heureFin") LocalTime heureFin,@Param("jour") LocalDate jour,@Param("materiel") Materiel materiel);

}