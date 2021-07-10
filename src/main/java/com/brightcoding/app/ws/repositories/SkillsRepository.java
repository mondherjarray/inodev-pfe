package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.SkillsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SkillsRepository extends CrudRepository<SkillsEntity, Long> {
    List<SkillsEntity> findByCondidat(CondidatEntity currentCondidat);

    SkillsEntity findBySkillsId(String skillsId);
    public SkillsEntity save(SkillsEntity skillls);
}
