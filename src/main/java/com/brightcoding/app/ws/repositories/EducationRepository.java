package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.AddressEntity;
import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.EducationEntity;
import com.brightcoding.app.ws.entities.UserEntity;
import com.brightcoding.app.ws.shared.dto.EducationDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EducationRepository extends CrudRepository<EducationEntity, Long> {
    List<EducationEntity> findByCondidat(CondidatEntity currentCondidat);

    EducationEntity findByEducationId(String educationId);
    public EducationEntity save(EducationEntity edducation);
}
