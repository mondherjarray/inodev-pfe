package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.Event;
import com.brightcoding.app.ws.entities.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
    @Query("SELECT t FROM Resource t where t.Id = :id")
    Resource findByiId (Long id);
}
