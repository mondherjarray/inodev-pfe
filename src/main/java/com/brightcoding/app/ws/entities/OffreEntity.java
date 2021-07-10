package com.brightcoding.app.ws.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "offre")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class OffreEntity {
    private static final long serialVersionUID = -5763827745308343856L;

    @GeneratedValue
    @Id
    private Integer id;
    private String image;
    private String type;
    private String offreId;
    private String nom;
    private String organisation;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String description;
    private String video;
    private String duree;
    private String niveau;
    private Long prix;
    private Long supervisor;
    private String processus;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="offre" )
    List<CondidatOffreEntity> condidatoffre;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="offre" )
    private List<SkillsEntity> skills;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="offre" )
    private List<SpecialiteEntity> specialite;
    @OneToMany(cascade = CascadeType.ALL,mappedBy="offre" )
    private List<Event> event;

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    public Integer getId() {
        return id;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOffreId() {
        return offreId;
    }

    public void setOffreId(String offreId) {
        this.offreId = offreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public String getProcessus() {
        return processus;
    }

    public void setProcessus(String processus) {
        this.processus = processus;
    }

    public List<CondidatOffreEntity> getCondidatoffre() {
        return condidatoffre;
    }

    public void setCondidatoffre(List<CondidatOffreEntity> condidatoffre) {
        this.condidatoffre = condidatoffre;
    }

    public List<SkillsEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsEntity> skills) {
        this.skills = skills;
    }

    public List<SpecialiteEntity> getSpecialite() {
        return specialite;
    }

    public void setSpecialite(List<SpecialiteEntity> specialite) {
        this.specialite = specialite;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Offre [id=" + id + ", image=" + image + ", type=" + type + ", offreId=" + offreId + ", nom=" + nom
                + ", organisation=" + organisation + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", description=" + description + ", video=" + video
                + ", prix=" + prix + ", processus=" + processus +" , duree=" + duree + ", niveau=" + niveau + "]";
    }
    public OffreEntity () {
        super();
    }
}
