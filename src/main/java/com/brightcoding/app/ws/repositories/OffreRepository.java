package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.OffreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<OffreEntity, Integer> {
    OffreEntity findByOffreId(String id);
    @Query("SELECT t FROM OffreEntity t where t.id = :id")
    OffreEntity findByaId(Integer id);
    @Query("SELECT t.id, t.nom, t.image, t.id, t.duree, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.type, t.offreId, t.video FROM OffreEntity t where t.type = :type and t.dateFin >= current_date ")
    List findAll(String type);
    @Query("SELECT t.id, t.nom, t.image, t.id, t.duree, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.type, t.offreId, t.video FROM OffreEntity t where t.dateFin >= current_date ")
    List findAllcurrent();
    @Query("SELECT t.id, t.nom, t.image, t.id, t.duree, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.type, t.offreId, t.video FROM OffreEntity t where t.type = :type ")
    List findaAll(String type);
    @Query("SELECT t.offreId, t.nom, t.id FROM OffreEntity t")
    List findAllByNom();
    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t where t.id = :id and t.type = :type ")
    List findByid(Integer id, String type);
    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t, SpecialiteEntity s where s.nom = :id and s.offre.id = t.id and t.type = :type and t.dateFin >= current_date ")
    List findBycat(String id, String type);
    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t, SpecialiteEntity s where s.nom = :id and s.offre.id = t.id and t.type = :type and t.dateFin >= current_date ")
    List findBycategorie(String id, String type);


    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t, SpecialiteEntity s " +
            "where s.nom = :spe and s.offre.id = t.id and t.niveau = :niveau and t.duree = :duree and t.type = :type and t.dateFin >= current_date")
    List findBysearch(String spe, String duree, String niveau, String type);

    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t " +
            " where t.niveau = :niveau and t.duree = :duree and t.type = :type and t.dateFin >= current_date")
    List findByspenull( String duree, String niveau, String type);

    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t, SpecialiteEntity s " +
                "where s.nom = :spe and s.offre.id = t.id and t.duree = :duree and t.type = :type and t.dateFin >= current_date")
    List findBynivnull(String spe, String duree, String type);

    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t, SpecialiteEntity s " +
            "where s.nom = :spe and s.offre.id = t.id and t.niveau = :niveau and t.type = :type and t.dateFin >= current_date")
    List findBydurnull(String spe, String niveau, String type);

    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t " +
            "where  t.niveau = :niveau and t.type = :type and t.dateFin >= current_date")
    List findBysdnull( String niveau, String type);

    @Query("SELECT t.id, t.nom, t.image, t.id, t.dateDebut, t.dateFin, t.description, t.organisation," +
            " t.prix, t.processus, t.duree, t.niveau, t.type, t.offreId, t.video FROM OffreEntity t " +
            "where t.duree = :duree and t.type = :type and t.dateFin >= current_date")
    List findBynsnull(String duree, String type);
}
