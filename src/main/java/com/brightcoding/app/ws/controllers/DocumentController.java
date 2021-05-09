package com.brightcoding.app.ws.controllers;

import com.brightcoding.app.ws.entities.OffreEntity;
import com.brightcoding.app.ws.repositories.DocumentRepository;
import com.brightcoding.app.ws.requests.DocumentRequest;
import com.brightcoding.app.ws.responses.DocumentResponse;
import com.brightcoding.app.ws.services.DocumentService;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.DocumentDto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    ServletContext context;
    @Autowired
    DocumentService educationService;
    @Autowired
    DocumentRepository repository;
    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getDocument(Principal principal) {

        List<DocumentDto> education = educationService.getAllDocument(principal.getName());

        Type listType = new TypeToken<List<DocumentResponse>>() {}.getType();
        List<DocumentResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<DocumentResponse>>(educationResponse, HttpStatus.OK);


    }
    @GetMapping("/get/{principal}")
    public ResponseEntity<List<DocumentResponse>> getDocumentuser(@PathVariable String principal) {

        List<DocumentDto> education = educationService.getAllDocument(principal);

        Type listType = new TypeToken<List<DocumentResponse>>() {}.getType();
        List<DocumentResponse> educationResponse = new ModelMapper().map(education, listType);

        return new ResponseEntity<List<DocumentResponse>>(educationResponse, HttpStatus.OK);


    }

    @PostMapping(
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<DocumentResponse> StoreEducation(@RequestPart("file") MultipartFile file, Principal principale) {
        boolean isExit = new File(context.getRealPath("src/web/doc/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("src/web/doc/")).mkdir();
            System.out.println("mk dir.............");
        }
        if (file.getName() != null) {
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
            File serverFile = new File (context.getRealPath("src/web/doc/"+File.separator+newFileName));
            String distfile = "src/web/doc/"+ file.getOriginalFilename();
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
        ModelMapper modelMapper = new ModelMapper();

        DocumentDto educationDto = modelMapper.map(file, DocumentDto.class);
        educationDto.setNom(file.getOriginalFilename());

        DocumentDto createEducation = educationService.createDocument(educationDto, principale.getName());
        createEducation.setNom(file.getOriginalFilename());

        DocumentResponse newEducation = modelMapper.map(createEducation, DocumentResponse.class);

        newEducation.setNom(file.getOriginalFilename());
        return new ResponseEntity<DocumentResponse>(newEducation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<DocumentResponse> getOneDocument(@PathVariable(name="id") String educationId) {

        DocumentDto educationDto = educationService.getDocument(educationId);

        ModelMapper modelMapper = new ModelMapper();

        DocumentResponse educationResponse = modelMapper.map(educationDto, DocumentResponse.class);

        return new ResponseEntity<DocumentResponse>(educationResponse, HttpStatus.OK);
    }
    @GetMapping(path="/doc/{id}")
    public byte[] getPhoto(@PathVariable String id) throws Exception{
        DocumentDto doc = educationService.getDocument(id);
        return Files.readAllBytes(Paths.get("C:/Users/jarra/Downloads/inodevConception/spring-boot-v1/src/web/doc/"+doc.getNom()));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable(name="id") String educationId) {

        educationService.deleteDocument(educationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            path="/{id}",
            consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<DocumentResponse> updateDocument(@PathVariable String id , @RequestBody DocumentRequest educationRequest) {

        DocumentDto educationDto = new DocumentDto();

        BeanUtils.copyProperties(educationRequest, educationDto);

        DocumentDto updateEducation = educationService.updateDocument(id, educationDto);

        DocumentResponse educationResponse = new DocumentResponse();

        BeanUtils.copyProperties(updateEducation, educationResponse);

        return new ResponseEntity<DocumentResponse>(educationResponse, HttpStatus.ACCEPTED);
    }
}
