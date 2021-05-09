package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.DocumentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByCondidat(CondidatEntity currentCondidat);

    DocumentEntity findByDocumentId(String documentId);
    public DocumentEntity save(DocumentEntity document);
}
