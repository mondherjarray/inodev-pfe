package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.requests.OriginRequest;
import com.brightcoding.app.ws.responses.OriginResponse;
import com.brightcoding.app.ws.services.OriginService;
import com.brightcoding.app.ws.shared.dto.OriginDto;
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
@RequestMapping("/source")
public class SourceController {

    @Autowired
    OriginService educationService;

    @GetMapping
    public ResponseEntity<List<OriginResponse>> getSource(Principal principal) {

        List<OriginDto> education = educationService.getAllSource(principal.getName());

        Type listType = new TypeToken<List<OriginResponse>>() {}.getType();
        List<OriginResponse> OriginResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<OriginResponse>>(OriginResponse, HttpStatus.OK);

    }

    @PostMapping(
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<OriginResponse> StoreSource(@RequestBody OriginRequest OriginRequest, Principal principale) {

        ModelMapper modelMapper = new ModelMapper();

        OriginDto OriginDto = modelMapper.map(OriginRequest, OriginDto.class);
        OriginDto createEducation = educationService.createSource(OriginDto, principale.getName());

        OriginResponse newEducation = modelMapper.map(createEducation, OriginResponse.class);

        return new ResponseEntity<OriginResponse>(newEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<OriginResponse> getOneSource(@PathVariable(name="id") String educationId) {

        OriginDto OriginDto = educationService.getSource(educationId);

        ModelMapper modelMapper = new ModelMapper();

        OriginResponse OriginResponse = modelMapper.map(OriginDto, OriginResponse.class);

        return new ResponseEntity<OriginResponse>(OriginResponse, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSource(@PathVariable(name="id") String educationId) {

        educationService.deleteSource(educationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<OriginResponse> updateSource(@PathVariable String id , @RequestBody OriginRequest OriginRequest) {

        OriginDto OriginDto = new OriginDto();

        BeanUtils.copyProperties(OriginRequest, OriginDto);

        OriginDto updateEducation = educationService.updateSource(id, OriginDto);

        OriginResponse OriginResponse = new OriginResponse();

        BeanUtils.copyProperties(updateEducation, OriginResponse);

        return new ResponseEntity<OriginResponse>(OriginResponse, HttpStatus.ACCEPTED);
    }
}
