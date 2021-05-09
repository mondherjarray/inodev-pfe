package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.DocumentEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.DocumentRepository;
import com.brightcoding.app.ws.services.DocumentService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.DocumentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class DocumentServiceImp implements DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    CondidatRepository condidatRepository;

    @Autowired
    Utils util;

    @Override
    public List<DocumentDto> getAllDocument(String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        List<DocumentEntity> documents = currentCondidat.getAdmin() == 1?  (List<DocumentEntity>) documentRepository.findAll() : documentRepository.findByCondidat(currentCondidat);

        Type listType = new TypeToken<List<DocumentEntity>>() {}.getType();
        List<DocumentDto> documentsDto = new ModelMapper().map(documents, listType);

        return documentsDto;
    }


    @Override
    public DocumentDto createDocument(DocumentDto document, String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(currentCondidat, CondidatDto.class);
        document.setDocumentId(util.generateStringId(30));
        document.setCondidat(condidatDto);

        DocumentEntity documentEntity = modelMapper.map(document, DocumentEntity.class);

        DocumentEntity newDocument = documentRepository.save(documentEntity);

        DocumentDto documentDto = modelMapper.map(newDocument, DocumentDto.class);

        return documentDto;
    }


    @Override
    public DocumentDto getDocument(String documentId) {

        DocumentEntity documentEntityEntity = documentRepository.findByDocumentId(documentId);

        ModelMapper modelMapper = new ModelMapper();

        DocumentDto documentDto = modelMapper.map(documentEntityEntity, DocumentDto.class);

        return documentDto;
    }

    @Override
    public void deleteDocument(String documentId) {

        DocumentEntity document = documentRepository.findByDocumentId(documentId);

        if(document == null) throw new RuntimeException("education not found");

        documentRepository.delete(document);

    }
    @Override
    public DocumentDto updateDocument(String id, DocumentDto documentDto) {

        DocumentEntity documentEntity = documentRepository.findByDocumentId(id);


        if(documentDto == null) throw new UsernameNotFoundException(id);
        documentDto.setNom(documentDto.getNom());


        DocumentEntity educ = documentRepository.save(documentEntity);

        DocumentDto educt = new DocumentDto();

        BeanUtils.copyProperties(educ, educt);

        return educt;
    }


}
