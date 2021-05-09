package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.CondidatOffreEntity;
import com.brightcoding.app.ws.entities.OffreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondidatOffreRepository extends JpaRepository<CondidatOffreEntity, Long> {

    @Query("SELECT t.statut FROM CondidatOffreEntity t where t.offre.id = :offre and t.condidat.id = :condidat")
    Integer findreg(Integer offre, Long condidat);
    @Query("SELECT t.id FROM CondidatOffreEntity t where t.offre.id = :offre and t.condidat.id = :condidat")
    Long finddet(Integer offre, Long condidat);
    @Query("SELECT t FROM CondidatOffreEntity t where t.id = :offre")
    CondidatOffreEntity findByaId(Long offre);
    @Query("SELECT t.registeredAt, o.nom, o.processus, o.type FROM CondidatOffreEntity t, OffreEntity o where t.condidat.id = :id and t.offre.id = o.id")
    List findBycondidat(Long id);

    @Query("SELECT c.userId, c.email, c.firstName, c.lastName , ta.registeredAt FROM CondidatOffreEntity ta, CondidatEntity c where ta.offre.id = :offre and ta.condidat.id = c.id")
    List filter(Integer offre);

}
