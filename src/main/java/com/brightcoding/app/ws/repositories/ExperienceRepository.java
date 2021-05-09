package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.ExperienceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExperienceRepository extends CrudRepository<ExperienceEntity, Long> {
    List<ExperienceEntity> findByCondidat(CondidatEntity currentCondidat);

    ExperienceEntity findByExperienceId(String experienceId);
    public ExperienceEntity save(ExperienceEntity experience);
}
