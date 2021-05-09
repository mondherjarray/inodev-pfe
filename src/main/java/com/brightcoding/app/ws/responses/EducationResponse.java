package com.brightcoding.app.ws.responses;

import com.brightcoding.app.ws.entities.EducationEntity;
import com.brightcoding.app.ws.shared.dto.CondidatDto;

import java.util.Date;
import java.util.List;

public class EducationResponse {

    private Integer id;
    private String nomFaculte;
    private String educationId;

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    private String pays;
    private Date dateDebut;
    private Date dateFin;
    private String nomDiplome;
    private String specialite;
    private String niveau;

    public CondidatResponse getCondidatResponse() {
        return condidatResponse;
    }

    public void setCondidatResponse(CondidatResponse condidatResponse) {
        this.condidatResponse = condidatResponse;
    }

    private CondidatResponse condidatResponse;
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

}
