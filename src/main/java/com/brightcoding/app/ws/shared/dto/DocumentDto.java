package com.brightcoding.app.ws.shared.dto;

import com.brightcoding.app.ws.entities.CondidatEntity;


public class DocumentDto {
    private Integer id;
    private String  documentId;
    private String nom;
    private CondidatDto condidat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CondidatDto getCondidat() {
        return condidat;
    }

    public void setCondidat(CondidatDto condidat) {
        this.condidat = condidat;
    }
}
