package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.SkillsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillsRepository extends CrudRepository<SkillsEntity, Long> {
    List<SkillsEntity> findByCondidat(CondidatEntity currentCondidat);

    SkillsEntity findBySkillsId(String skillsId);


    public SkillsEntity save(SkillsEntity skillls);
}
