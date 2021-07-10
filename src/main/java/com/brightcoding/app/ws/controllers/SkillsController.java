package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.entities.SkillsEntity;
import com.brightcoding.app.ws.repositories.SkillsRepository;
import com.brightcoding.app.ws.requests.SkillsRequest;
import com.brightcoding.app.ws.responses.SkillsResponse;
import com.brightcoding.app.ws.services.SkillsService;
import com.brightcoding.app.ws.shared.dto.SkillsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    SkillsService educationService;
    @Autowired
    SkillsRepository re ;

    @GetMapping
    public ResponseEntity<List<SkillsResponse>> getSkills(Principal principal) {

        List<SkillsDto> education = educationService.getAllSkills(principal.getName());

        Type listType = new TypeToken<List<SkillsResponse>>() {}.getType();
        List<SkillsResponse> SkillsResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<SkillsResponse>>(SkillsResponse, HttpStatus.OK);

    }
    @GetMapping("/get/{principal}")
    public ResponseEntity<List<SkillsResponse>> getSkils(@PathVariable String principal) {

        List<SkillsDto> education = educationService.getAllSkills(principal);

        Type listType = new TypeToken<List<SkillsResponse>>() {}.getType();
        List<SkillsResponse> SkillsResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<SkillsResponse>>(SkillsResponse, HttpStatus.OK);

    }

    @PostMapping(
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<SkillsResponse> StoreSkills(@RequestBody SkillsRequest SkillsRequest, Principal principale) {

        ModelMapper modelMapper = new ModelMapper();

        SkillsDto SkillsDto = modelMapper.map(SkillsRequest, SkillsDto.class);
        SkillsDto createEducation = educationService.createSkills(SkillsDto, principale.getName());

        SkillsResponse newEducation = modelMapper.map(createEducation, SkillsResponse.class);

        return new ResponseEntity<SkillsResponse>(newEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<SkillsResponse> getOneSkills(@PathVariable(name="id") String educationId) {

        SkillsDto SkillsDto = educationService.getSkills(educationId);

        ModelMapper modelMapper = new ModelMapper();

        SkillsResponse SkillsResponse = modelMapper.map(SkillsDto, SkillsResponse.class);

        return new ResponseEntity<SkillsResponse>(SkillsResponse, HttpStatus.OK);
    }
    @GetMapping("/offre")
    public   Iterable<SkillsEntity> getOoffre() {

        Iterable<SkillsEntity> skillsDto = re.findAll();
        ModelMapper modelMapper = new ModelMapper();
        SkillsEntity skillsResponse = modelMapper.map(skillsDto, SkillsEntity.class);
        return skillsDto;
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkills(@PathVariable(name="id") String educationId) {

        educationService.deleteSkills(educationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<SkillsResponse> updateSkills(@PathVariable String id , @RequestBody SkillsRequest SkillsRequest) {

        SkillsDto SkillsDto = new SkillsDto();

        BeanUtils.copyProperties(SkillsRequest, SkillsDto);

        SkillsDto updateEducation = educationService.updateSkills(id, SkillsDto);

        SkillsResponse SkillsResponse = new SkillsResponse();

        BeanUtils.copyProperties(updateEducation, SkillsResponse);

        return new ResponseEntity<SkillsResponse>(SkillsResponse, HttpStatus.ACCEPTED);
    }
}
