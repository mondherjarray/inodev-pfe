package com.brightcoding.app.ws.controllers;


import com.brightcoding.app.ws.requests.QuestionRequest;
import com.brightcoding.app.ws.responses.QuestionResponse;
import com.brightcoding.app.ws.services.QuestionService;
import com.brightcoding.app.ws.shared.dto.QuestionDto;
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
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService educationService;

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getQuestion(Principal principal) {

        List<QuestionDto> education = educationService.getAllQuestion(principal.getName());

        Type listType = new TypeToken<List<QuestionResponse>>() {}.getType();
        List<QuestionResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<QuestionResponse>>(educationResponse, HttpStatus.OK);

    }

    @PostMapping(
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<QuestionResponse> StoreEducation(@RequestBody QuestionRequest educationRequest, Principal principale) {

        ModelMapper modelMapper = new ModelMapper();

        QuestionDto educationDto = modelMapper.map(educationRequest, QuestionDto.class);
        QuestionDto createEducation = educationService.createQuestion(educationDto, principale.getName());

        QuestionResponse newEducation = modelMapper.map(createEducation, QuestionResponse.class);

        return new ResponseEntity<QuestionResponse>(newEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<QuestionResponse> getOneQuestion(@PathVariable(name="id") String educationId) {

        QuestionDto educationDto = educationService.getQuestion(educationId);

        ModelMapper modelMapper = new ModelMapper();

        QuestionResponse educationResponse = modelMapper.map(educationDto, QuestionResponse.class);

        return new ResponseEntity<QuestionResponse>(educationResponse, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(name="id") String educationId) {

        educationService.deleteQuestion(educationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable String id , @RequestBody QuestionRequest educationRequest) {

        QuestionDto educationDto = new QuestionDto();

        BeanUtils.copyProperties(educationRequest, educationDto);

        QuestionDto updateEducation = educationService.updateQuestion(id, educationDto);

        QuestionResponse educationResponse = new QuestionResponse();

        BeanUtils.copyProperties(updateEducation, educationResponse);

        return new ResponseEntity<QuestionResponse>(educationResponse, HttpStatus.ACCEPTED);
    }
}
