package com.brightcoding.app.ws.requests;

public class SkillsRequest {
    private Integer id;
    private String skillsId;
    private String niveau;
    private String nom;
    private Boolean certifier;

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
}
