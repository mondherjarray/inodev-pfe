package com.brightcoding.app.ws.repositories;

import com.brightcoding.app.ws.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("from Event e where not(e.end < :from and e.start > :to) and e.offre.id = :id")
    public List<Event> findBetween(@Param("from") LocalDateTime start, @Param("to") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime end, @Param("id") Integer id);

}
