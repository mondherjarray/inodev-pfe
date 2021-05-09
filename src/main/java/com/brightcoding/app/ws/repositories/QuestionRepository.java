package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByCondidat(CondidatEntity currentCondidat);

    QuestionEntity findByQuestionId(String questionId);
    public QuestionEntity save(QuestionEntity question);
}
