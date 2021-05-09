package com.brightcoding.app.ws.entities;

import javax.persistence.*;

@Entity
@Table(name = "specialite")
public class SpecialiteEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String  specialiteId;
    private String nom;
    private String image;
    @ManyToOne
    private OffreEntity offre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialiteId() {
        return specialiteId;
    }

    public void setSpecialiteId(String specialiteId) {
        this.specialiteId = specialiteId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public OffreEntity getOffre() {
        return offre;
    }

    public void setOffre(OffreEntity offre) {
        this.offre = offre;
    }
}
