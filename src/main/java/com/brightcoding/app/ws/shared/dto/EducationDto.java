package com.brightcoding.app.ws.shared.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class EducationDto
{


    private Integer id;

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    private String educationId;
    private String nomFaculte;
    private String pays;
    private Date dateDebut;
    private Date dateFin;
    private String nomDiplome;
    private String specialite;
    private String niveau;
    private CondidatDto condidat;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomFaculte() {
        return nomFaculte;
    }

    public void setNomFaculte(String nomFaculte) {
        this.nomFaculte = nomFaculte;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomDiplome() {
        return nomDiplome;
    }

    public void setNomDiplome(String nomDiplome) {
        this.nomDiplome = nomDiplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public CondidatDto getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatDto condidat) {
        this.condidat = condidat;
    }
}
