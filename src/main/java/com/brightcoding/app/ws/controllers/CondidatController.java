package com.brightcoding.app.ws.controllers;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.requests.CondidatRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brightcoding.app.ws.responses.CondidatResponse;
import com.brightcoding.app.ws.services.CondidatService;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/condidat")
public class CondidatController {
    @Autowired
    CondidatService condidatService;
    @Autowired
    CondidatRepository repository;
    @Autowired
    ServletContext context;

    @GetMapping(produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CondidatResponse>> getAllCondidats(@RequestParam(value="page", defaultValue = "1") int page, @RequestParam(value="limit", defaultValue = "20")  int limit , @RequestParam(value="search", defaultValue = "") String search, @RequestParam(value="status", defaultValue = "1") int status) {

        List<CondidatResponse> condidatsResponse = new ArrayList<>();

        List<CondidatDto> condidats = condidatService.getCondidat(page, limit, search, status);

        for(CondidatDto condidatDto: condidats) {

            ModelMapper modelMapper = new ModelMapper();
            CondidatResponse condidatResponse =  modelMapper.map(condidatDto, CondidatResponse.class);

            condidatsResponse.add(condidatResponse);
        }
        return new ResponseEntity<List<CondidatResponse>>(condidatsResponse, HttpStatus.OK);
    }

        @PostMapping(
    )

    public ResponseEntity<CondidatResponse> createCondidat(
            @RequestBody CondidatRequest condidatRequest) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(condidatRequest, CondidatDto.class);
            condidatDto.setAdmin(0);
        CondidatDto createCondidat = condidatService.createCondidat(condidatDto);

        CondidatResponse condidatResponse =  modelMapper.map(createCondidat, CondidatResponse.class);

            return new ResponseEntity<CondidatResponse>(condidatResponse, HttpStatus.CREATED);

    }
    @PostMapping("/suprvisor"
    )

    public ResponseEntity<CondidatResponse> createsupervisor(
            @RequestBody @Valid CondidatRequest condidatRequest) throws Exception {


        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(condidatRequest, CondidatDto.class);
        condidatDto.setAdmin(2);
        CondidatDto createCondidat = condidatService.createCondidat(condidatDto);

        CondidatResponse condidatResponse =  modelMapper.map(createCondidat, CondidatResponse.class);

        return new ResponseEntity<CondidatResponse>(condidatResponse, HttpStatus.CREATED);

    }
    @PostMapping("/company"
    )

    public ResponseEntity<CondidatResponse> createsociete(
            @RequestBody @Valid CondidatRequest condidatRequest) throws Exception {


        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(condidatRequest, CondidatDto.class);
        condidatDto.setAdmin(3);
        CondidatDto createCondidat = condidatService.createCondidat(condidatDto);

        CondidatResponse condidatResponse =  modelMapper.map(createCondidat, CondidatResponse.class);
        condidatResponse.setAdmin(3);
        return new ResponseEntity<CondidatResponse>(condidatResponse, HttpStatus.CREATED);

    }
    @GetMapping(path="/{userId}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CondidatResponse> getCondidat(@PathVariable String userId) {

        CondidatDto condidatDto = condidatService.getCondidatByUserId(userId);

        CondidatResponse condidatResponse = new CondidatResponse();

        BeanUtils.copyProperties(condidatDto, condidatResponse);

        return new ResponseEntity<CondidatResponse>(condidatResponse, HttpStatus.OK);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}
    )
    public ResponseEntity<CondidatResponse> updateCondidat(
            @PathVariable String id , @RequestPart(value = "condidat") String condidatRequest,
            @RequestPart(value = "photo", required = false) MultipartFile file) throws JsonProcessingException {

        CondidatDto condidatDto = new CondidatDto();
        CondidatEntity off = new ObjectMapper().readValue(condidatRequest, CondidatEntity.class);

        CondidatDto updateCondidat = condidatService.updateCondidat(id, off);
        boolean isExit = new File(context.getRealPath("src/web/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("src/web/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        if (file.getName() != null) {
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

        }catch(Exception e) {
            e.printStackTrace();
        }
        }
        CondidatResponse condidatResponse = new CondidatResponse();
        updateCondidat.setPhoto(file.getOriginalFilename()  );
        BeanUtils.copyProperties(updateCondidat, condidatResponse);

        return new ResponseEntity<CondidatResponse>(condidatResponse, HttpStatus.ACCEPTED);
    }
    @GetMapping(path="/img/{id}")
    public byte[] getPhoto(@PathVariable String id) throws Exception{
        CondidatDto condidatDto = condidatService.getCondidatByUserId(id);
        return Files.readAllBytes(Paths.get("C:/Users/jarra/Downloads/inodevConception/spring-boot-v1/src/web/Images/"+condidatDto.getPhoto()));

    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {

        condidatService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
