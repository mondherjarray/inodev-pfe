package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.requests.ExperienceRequest;
import com.brightcoding.app.ws.responses.ExperienceResponse;
import com.brightcoding.app.ws.services.ExperienceService;
import com.brightcoding.app.ws.shared.dto.ExperienceDto;
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
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    ExperienceService educationService;

    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> getExperience(Principal principal) {

        List<ExperienceDto> education = educationService.getAllExperience(principal.getName());

        Type listType = new TypeToken<List<ExperienceResponse>>() {}.getType();
        List<ExperienceResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<ExperienceResponse>>(educationResponse, HttpStatus.OK);

    }
    @GetMapping("/get/{principal}")
    public ResponseEntity<List<ExperienceResponse>> getExperienc(@PathVariable String principal) {

        List<ExperienceDto> education = educationService.getAllExperience(principal);

        Type listType = new TypeToken<List<ExperienceResponse>>() {}.getType();
        List<ExperienceResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<ExperienceResponse>>(educationResponse, HttpStatus.OK);

    }

    @PostMapping(
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ExperienceResponse> StoreExperience(@RequestBody ExperienceRequest educationRequest, Principal principale) {

        ModelMapper modelMapper = new ModelMapper();

        ExperienceDto educationDto = modelMapper.map(educationRequest, ExperienceDto.class);
        ExperienceDto createEducation = educationService.createExperience(educationDto, principale.getName());

        ExperienceResponse newEducation = modelMapper.map(createEducation, ExperienceResponse.class);

        return new ResponseEntity<ExperienceResponse>(newEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ExperienceResponse> getOneExperience(@PathVariable(name="id") String educationId) {

        ExperienceDto educationDto = educationService.getExperience(educationId);

        ModelMapper modelMapper = new ModelMapper();

        ExperienceResponse educationResponse = modelMapper.map(educationDto, ExperienceResponse.class);

        return new ResponseEntity<ExperienceResponse>(educationResponse, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable(name="id") String educationId) {

        educationService.deleteExperience(educationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<ExperienceResponse> updateEducation(@PathVariable String id , @RequestBody ExperienceRequest educationRequest) {

        ExperienceDto educationDto = new ExperienceDto();

        BeanUtils.copyProperties(educationRequest, educationDto);

        ExperienceDto updateEducation = educationService.updateExperience(id, educationDto);

        ExperienceResponse educationResponse = new ExperienceResponse();

        BeanUtils.copyProperties(updateEducation, educationResponse);

        return new ResponseEntity<ExperienceResponse>(educationResponse, HttpStatus.ACCEPTED);
    }
}
