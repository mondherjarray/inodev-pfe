package com.brightcoding.app.ws.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class SkillsEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String skillsId;
    private String niveau;
    private String nom;
    private Boolean certifier;
    @ManyToOne
    private CondidatEntity condidat;
    @ManyToOne
    private OffreEntity offre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(String skillsId) {
        this.skillsId = skillsId;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getCertifier() {
        return certifier;
    }

    public void setCertifier(Boolean certifier) {
        this.certifier = certifier;
    }

    public CondidatEntity getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatEntity condidat) {
        this.condidat = condidat;
    }
}
