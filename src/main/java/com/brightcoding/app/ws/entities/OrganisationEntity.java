package com.brightcoding.app.ws.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "organisation")
public class OrganisationEntity extends UserEntity implements Serializable {

    @Column(nullable=true, length=50)
    private String Name;

    @Column(nullable=true, length=50)
    private String Description ;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



}
