package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.OriginEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OriginRepository extends CrudRepository<OriginEntity, Long> {
    List<OriginEntity> findByCondidat(CondidatEntity currentCondidat);

    OriginEntity findBySourceId(String sourceId);
    public OriginEntity save(OriginEntity source);
}
