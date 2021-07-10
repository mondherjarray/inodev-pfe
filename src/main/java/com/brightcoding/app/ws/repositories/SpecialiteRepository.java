package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.OffreEntity;
import com.brightcoding.app.ws.entities.SpecialiteEntity;
import com.brightcoding.app.ws.responses.SpecialiteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialiteRepository extends JpaRepository<SpecialiteEntity, String> {

    SpecialiteEntity findBySpecialiteId(String id);
    @Query("SELECT t.nom, t.image, t.offre.id, t.specialiteId FROM SpecialiteEntity t where t.nom = :name")
    List findByName(@Param("name") String name);
    @Query("SELECT distinct t.nom , t.image,t.offre.id, t.specialiteId FROM SpecialiteEntity t where t.image is not null")
    List findAll();
    @Query("SELECT t.nom, t.image, t.offre.id, t.specialiteId FROM SpecialiteEntity t")
    List findbycat();
    @Query("SELECT t.nom, t.image, t.offre.id, t.specialiteId FROM SpecialiteEntity t where t.offre.id = :id")
    List findByoffreID(@Param("id") Integer id);

    @Query("SELECT MAX (t.id) FROM OffreEntity t")
    Integer maxId();
}
