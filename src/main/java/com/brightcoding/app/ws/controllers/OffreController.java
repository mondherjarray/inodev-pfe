package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.entities.OffreEntity;
import com.brightcoding.app.ws.entities.SpecialiteEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.OffreRepository;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("/offre")
public class OffreController {
        @Autowired
        OffreRepository repository;
        @Autowired
        CondidatRepository condidatRepository;
        @Autowired
        ServletContext context;
    @Autowired
    Utils util;
    @GetMapping
    public List getAllOffres() {

        List  offres = new ArrayList<>();
        repository.findAllcurrent().forEach(offres::add);

        return offres;
    }
    @GetMapping("/getaAll/{type}")
    public List getAllOffresAdmin(@PathVariable String type) {

        List  offres = new ArrayList<>();
        repository.findaAll(type).forEach(offres::add);

        return offres;
    }
    @GetMapping ("/getAll")
    public ResponseEntity<List<String>> getAll()
    {
        List<String> listof = new ArrayList<String>();
        String filesPath = context.getRealPath("src/web/images/");
        File filefolder = new File(filesPath);
        if (filefolder != null)
        {
            for (File file :filefolder.listFiles())
            {
                if(!file.isDirectory())
                {
                    String encodeBase64 = null;
                    try {
                        String extension = FilenameUtils.getExtension(file.getName());
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bytes = new byte[(int)file.length()];
                        fileInputStream.read(bytes);
                        encodeBase64 = Base64.getEncoder().encodeToString(bytes);
                        listof.add("data:image/"+extension+";base64,"+encodeBase64);
                        fileInputStream.close();


                    }catch (Exception e){

                    }
                }
            }
        }
        return new ResponseEntity<List<String>>(listof, HttpStatus.OK);
    }
    @PostMapping
    public OffreEntity createOffre (@RequestParam("file") MultipartFile file,
                                                   @RequestParam("offre") String offre) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Ok .............");
        OffreEntity off = new ObjectMapper().readValue(offre, OffreEntity.class);
        boolean isExit = new File(context.getRealPath("src/web/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("src/web/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("src/web/Images/"+File.separator+newFileName));
        String distfile = "src/web/Images/"+ file.getOriginalFilename();
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
            Files.copy(file.getInputStream(),
                    Paths.get(distfile),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("............ok .............");

        }catch(Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(offre, off);
        off.setOffreId(util.generateStringId(30));
        off.setImage(newFileName);
        OffreEntity of = repository.save(off);
            return (of);

    }

    @GetMapping("/{id}/{type}")
    public List getOffreById(@PathVariable Integer id,@PathVariable String type)
             {
        List offre = repository.findByid(id, type);
        return offre;
    }
    @GetMapping("/spe")
    public List getOffId()
    {
        List offre = repository.findAllByNom();
        return offre;
    }
    @GetMapping("/id/{id}/{type}")
    public List getByid(@PathVariable Integer id,@PathVariable String type)
    {
        List offre = repository.findByid(id, type);
        return offre;
    }
    @GetMapping("/cat/{id}/{type}")
    public List getBysid(@PathVariable String id,@PathVariable String type)
    {
        List offre = repository.findBycategorie(id, type);
        return offre;
    }
    @GetMapping("/{spe}/{due}/{niveau}/{type}")
    public List getBysearch(@PathVariable String spe ,@PathVariable String due, @PathVariable String niveau, @PathVariable String type)
    {
        List offre = new ArrayList<>();
        if ((spe.contentEquals("null")) && (due.contentEquals("null")) && (niveau.contentEquals("null"))) {
            System.out.println("dfssssssssssssfddfdfdfdfffdfffdfdfdfdfdfdffdfddffddfdfdffdfdfdfdfdfdfdfdfd");
            repository.findAll(type).forEach(offre::add);
        }
        else if (niveau.contentEquals("null") && due.contentEquals("null")) {
             offre = repository.findBycat(spe, type);
        }
        else if (niveau.contentEquals("null") && spe.contentEquals("null")) {
             offre = repository.findBynsnull(due, type);
        }
        else if (spe.contentEquals("null") && due.contentEquals("null")) {
             offre = repository.findBysdnull(niveau, type);
        }
        else if (spe.contentEquals("null")) {
        offre = repository.findByspenull(due, niveau, type);
    }
        else if (due.contentEquals("null")) {
            offre = repository.findBydurnull(spe, niveau, type);
        }
        else if (niveau.contentEquals("null")) {
            offre = repository.findBynivnull(spe, due, type);
        }
        else {

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + spe + due +niveau);
             offre = repository.findBysearch(spe, due, niveau, type);
        }
        return offre;
    }
    @GetMapping(path="/Imgarticles/{id}")
    public byte[] getPhoto(@PathVariable("id") String id) throws Exception{
        OffreEntity offre = repository.findByOffreId(id);
        System.out.println(offre.getImage());
        System.out.println(context.getRealPath("/fdfd"));
        boolean isExit = new File("C:/Users/jarra/Downloads/inodevConception/spring-boot-v1/src/web/Images/"+offre.getImage()).exists();
        System.out.println(isExit);
        return Files.readAllBytes(Paths.get("C:/Users/jarra/Downloads/inodevConception/spring-boot-v1/src/web/Images/"+offre.getImage()));
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") String Id)
            {
        OffreEntity off = repository.findByOffreId(Id);
        repository.delete(off);
        return ("ok");
    }
    @PutMapping("edit/{id}")
    public String update(@PathVariable("id") String id, @RequestBody String off) throws JsonProcessingException {
        System.out.println("Update Article with ID = " + id + "...");
        OffreEntity offre = new ObjectMapper().readValue(off, OffreEntity.class);
        OffreEntity article = repository.findByOffreId(id);
        article.setNom(offre.getNom());
        article.setDateDebut(offre.getDateDebut());
        article.setDescription(offre.getDescription());
        article.setOrganisation(offre.getOrganisation());
        article.setDateFin(offre.getDateFin());
        article.setType(offre.getType());
        article.setDuree(offre.getDuree());
        article.setNiveau(offre.getNiveau());
        article.setImage(article.getImage());
        article.setPrix(offre.getPrix());
        article.setProcessus(offre.getProcessus());

        repository.save(article);
        return ("ok");

    }
}
