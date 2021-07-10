package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.entities.Event;
import com.brightcoding.app.ws.entities.OffreEntity;
import com.brightcoding.app.ws.entities.Resource;
import com.brightcoding.app.ws.repositories.EventRepository;
import com.brightcoding.app.ws.repositories.OffreRepository;
import com.brightcoding.app.ws.repositories.ResourceRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    EventRepository er;

    @Autowired
    ResourceRepository rr;
    @Autowired
    OffreRepository repository;

    @RequestMapping("/api")
    @ResponseBody
    String home() {
        return "Welcome!";
    }

    @RequestMapping("/api/resources")
    Iterable<Resource> resources() {
        return rr.findAll();
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to, @RequestParam("id")  Integer id) {
        return er.findBetween(from, to, id);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        Resource r = rr.findById(params.resource).get();
        OffreEntity offre = repository.findByaId(params.offre);
        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
        e.setResource(r);
        e.setOffre(offre);

        er.save(e);

        return e;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {

        Event e = er.findById(params.id).get();
        Resource r = rr.findById(params.resource).get();
        OffreEntity offre = repository.findByaId(params.offre);
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setResource(r);
        e.setOffre(offre);
        er.save(e);

        return e;
    }

    @PostMapping("/api/events/delete")
    @Transactional
    void deleteEvent(@RequestBody EventDeleteParams params) {
        er.deleteById(params.id);
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
        public Integer offre;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
        public Integer offre;
    }

    public static class EventDeleteParams {
        public Long id;
    }
}
