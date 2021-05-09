package com.brightcoding.app.ws.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
public class EducationEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String nomFaculte;
    private String pays;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String nomDiplome;
    private String specialite;
    private String niveau;

    @ManyToOne
    private CondidatEntity condidat;
    @Column(length=30, nullable=false)
    private String educationId;


    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public CondidatEntity getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatEntity condidat) {
        this.condidat = condidat;
    }



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
