package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.requests.CondidatRequest;
import com.brightcoding.app.ws.requests.EducationRequest;
import com.brightcoding.app.ws.responses.CondidatResponse;
import com.brightcoding.app.ws.responses.EducationResponse;
import com.brightcoding.app.ws.services.EducationService;

import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.EducationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users/education")
public class EducationController {

    @Autowired
    EducationService educationService;

    @GetMapping
    public ResponseEntity<List<EducationResponse>> getEducation(@AuthenticationPrincipal Principal principal) {

        List<EducationDto> education = educationService.getAllEducation(principal.getName());

        Type listType = new TypeToken<List<EducationResponse>>() {}.getType();
        List<EducationResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<EducationResponse>>(educationResponse, HttpStatus.OK);

    }
    @GetMapping("/get/{principal}")
    public ResponseEntity<List<EducationResponse>> getEducatin(@PathVariable String principal) {

        List<EducationDto> education = educationService.getAllEducation(principal);

        Type listType = new TypeToken<List<EducationResponse>>() {}.getType();
        List<EducationResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<EducationResponse>>(educationResponse, HttpStatus.OK);

    }

    @PostMapping(
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<EducationResponse> StoreEducation(@RequestBody EducationRequest educationRequest, Principal principale) {

        ModelMapper modelMapper = new ModelMapper();

        EducationDto educationDto = modelMapper.map(educationRequest, EducationDto.class);
        EducationDto createEducation = educationService.createEducation(educationDto, principale.getName());

        EducationResponse newEducation = modelMapper.map(createEducation, EducationResponse.class);

        return new ResponseEntity<EducationResponse>(newEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<EducationResponse> getOneEducation(@PathVariable(name="id") String educationId) {

        EducationDto educationDto = educationService.getEducation(educationId);

        ModelMapper modelMapper = new ModelMapper();

        EducationResponse educationResponse = modelMapper.map(educationDto, EducationResponse.class);

        return new ResponseEntity<EducationResponse>(educationResponse, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable(name="id") String educationId) {

        educationService.deleteEducation(educationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<EducationResponse> updateEducation(@PathVariable String id , @RequestBody EducationRequest educationRequest) {

        EducationDto educationDto = new EducationDto();

        BeanUtils.copyProperties(educationRequest, educationDto);

        EducationDto updateEducation = educationService.updateEducation(id, educationDto);

        EducationResponse educationResponse = new EducationResponse();

        BeanUtils.copyProperties(updateEducation, educationResponse);

        return new ResponseEntity<EducationResponse>(educationResponse, HttpStatus.ACCEPTED);
    }

}
