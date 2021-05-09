package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.AddressEntity;
import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.EducationEntity;
import com.brightcoding.app.ws.entities.UserEntity;
import com.brightcoding.app.ws.repositories.EducationRepository;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.services.EducationService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.AddressDto;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.EducationDto;
import com.brightcoding.app.ws.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
@Service
public class EducationServiceImp implements EducationService {

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    CondidatRepository condidatRepository;

    @Autowired
    Utils util;

    @Override
    public List<EducationDto> getAllEducation(String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        List<EducationEntity> educations = currentCondidat.getAdmin() == 1? (List<EducationEntity>) educationRepository.findAll() : educationRepository.findByCondidat(currentCondidat);

        Type listType = new TypeToken<List<EducationEntity>>() {}.getType();
        List<EducationDto> educationsDto = new ModelMapper().map(educations, listType);

        return educationsDto;
    }


    @Override
    public EducationDto createEducation(EducationDto education, String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(currentCondidat, CondidatDto.class);
        education.setEducationId(util.generateStringId(30));
        education.setCondidat(condidatDto);

        EducationEntity educationEntity = modelMapper.map(education, EducationEntity.class);

        EducationEntity newEducation = educationRepository.save(educationEntity);

        EducationDto educationDto = modelMapper.map(newEducation, EducationDto.class);

        return educationDto;
    }


    @Override
    public EducationDto getEducation(String educationId) {

        EducationEntity educationEntity = educationRepository.findByEducationId(educationId);

        ModelMapper modelMapper = new ModelMapper();

        EducationDto educationDto = modelMapper.map(educationEntity, EducationDto.class);

        return educationDto;
    }

    @Override
    public void deleteEducation(String educationId) {

        EducationEntity education = educationRepository.findByEducationId(educationId);

        if(education == null) throw new RuntimeException("education not found");

        educationRepository.delete(education);

    }
    @Override
    public EducationDto updateEducation(String id, EducationDto educationDto) {

        EducationEntity educationEntity = educationRepository.findByEducationId(id);


        if(educationDto == null) throw new UsernameNotFoundException(id);
        educationEntity.setNomDiplome(educationDto.getNomDiplome());
        educationEntity.setDateDebut(educationDto.getDateDebut());
        educationEntity.setDateFin(educationDto.getDateFin());
        educationEntity.setNiveau(educationDto.getNiveau());
        educationEntity.setPays(educationDto.getPays());
        educationEntity.setSpecialite(educationDto.getSpecialite());
        educationEntity.setNomFaculte(educationDto.getNomFaculte());

        EducationEntity educ = educationRepository.save(educationEntity);

        EducationDto educt = new EducationDto();

        BeanUtils.copyProperties(educ, educt);

        return educt;
    }



}
