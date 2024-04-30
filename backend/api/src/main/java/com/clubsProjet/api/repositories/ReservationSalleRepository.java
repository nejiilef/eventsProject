package com.clubsProjet.api.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubsProjet.api.models.ReservationSalle;
import com.clubsProjet.api.models.Salle;

public interface ReservationSalleRepository extends JpaRepository<ReservationSalle, Integer>{


	@Query("SELECT r FROM ReservationSalle r WHERE (((:heureDebut <= r.heureDebut AND :heureFin >= r.heureFin) or (:heureDebut <= r.heureDebut AND :heureFin >= r.heureDebut) or (:heureDebut <= r.heureFin AND :heureFin >= r.heureFin)) and :jour=r.jour and :salle=r.salle)")
	List<ReservationSalle> findReservationsByTimeRange(@Param("heureDebut") LocalTime heureDebut, @Param("heureFin") LocalTime heureFin,@Param("jour") LocalDate jour,@Param("salle") Salle salle);

}
