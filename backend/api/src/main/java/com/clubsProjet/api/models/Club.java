package com.clubsProjet.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter
@Getter
@Entity
@Table(name = "clubs")
public class Club {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String nom;
        private String description;
        private String image;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="chef_id")
    private UserEntity chef_id ;

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setChef_id(UserEntity chef_id) {
        this.chef_id = chef_id;
    }


}
