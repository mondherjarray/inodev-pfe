package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.entities.*;
import com.brightcoding.app.ws.repositories.CondidatOffreRepository;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.OffreRepository;
import com.brightcoding.app.ws.repositories.SpecialiteRepository;
import com.brightcoding.app.ws.shared.Utils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/condidatoffre")
public class CondidatOffreController {
    @Autowired
    CondidatOffreRepository repository;
    @Autowired
    CondidatRepository condidatRepository;
    @Autowired
    OffreRepository of;
    @Autowired
    ServletContext context;
    @Autowired
    Utils util;
    @PostMapping("/{id}")
    public String create (@PathVariable(name="id") Integer offreId, @RequestBody Integer specialite, Principal principale) throws JsonParseException, JsonMappingException, Exception
    {

        CondidatOffreEntity co = new CondidatOffreEntity();
        CondidatEntity currentCondidat = condidatRepository.findByEmail(principale.getName());
       OffreEntity offre = of.findByaId(offreId);
        Integer checkUser = repository.findreg(offre.getId(), currentCondidat.getId());
        if(checkUser != null) throw new RuntimeException("User Alrady Exists !");
        co.setOffre(offre);
        co.setStatut(specialite);
        co.setCondidat(currentCondidat);
        co.setRegisteredAt(LocalDateTime.now());
        co.setCondidatoffreId(util.generateStringId(30));
        CondidatOffreEntity of = repository.save(co);
        return ("okokok");

    }
    @GetMapping("/exist/{id}")
    public Integer exist (@PathVariable(name="id") Integer offreId, Principal principale) throws JsonParseException, JsonMappingException, Exception
    {

        CondidatOffreEntity co = new CondidatOffreEntity();
        CondidatEntity currentCondidat = condidatRepository.findByEmail(principale.getName());
        OffreEntity offre = of.findByaId(offreId);

        Integer checkUser = repository.findreg(offre.getId(), currentCondidat.getId());
        if (checkUser == null)
       return 0;
        else
            return checkUser;

    }
    @GetMapping("/myapp")
    public List myapp (Principal principale) throws JsonParseException, JsonMappingException, Exception
    {

        CondidatOffreEntity co = new CondidatOffreEntity();
        CondidatEntity currentCondidat = condidatRepository.findByEmail(principale.getName());

        List app = repository.findBycondidat(currentCondidat.getId());
            return app;

    }
    @GetMapping("/myapp/{id}")
    public List filter (@PathVariable Integer id) throws JsonParseException, JsonMappingException, Exception
    {
        List app = repository.filter(id);
        return app;

    }
    @DeleteMapping("/exist/{id}")
    public String delete (@PathVariable(name="id") Integer offreId, Principal principale) throws JsonParseException, JsonMappingException, Exception
    {

        CondidatOffreEntity co = new CondidatOffreEntity();
        CondidatEntity currentCondidat = condidatRepository.findByEmail(principale.getName());
        OffreEntity offre = of.findByaId(offreId);

        Long checkUser = repository.finddet(offre.getId(), currentCondidat.getId());

        CondidatOffreEntity c = repository.findByaId(checkUser);
        repository.delete(c);
 return "ok";
    }

}
