package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.ExperienceEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.ExperienceRepository;
import com.brightcoding.app.ws.services.ExperienceService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.ExperienceDto;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ExperienceServiceImp implements ExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    CondidatRepository condidatRepository;

    @Autowired
    Utils util;

    @Override
    public List<ExperienceDto> getAllExperience(String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        List<ExperienceEntity> experience = currentCondidat.getAdmin() == 1? (List<ExperienceEntity>) experienceRepository.findAll() : experienceRepository.findByCondidat(currentCondidat);

        Type listType = new TypeToken<List<ExperienceEntity>>() {}.getType();
        List<ExperienceDto> experienceDto = new ModelMapper().map(experience, listType);

        return experienceDto;
    }


    @Override
    public ExperienceDto createExperience(ExperienceDto experience, String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(currentCondidat, CondidatDto.class);
        experience.setExperienceId(util.generateStringId(30));
        experience.setCondidat(condidatDto);

        ExperienceEntity educationEntity = modelMapper.map(experience, ExperienceEntity.class);

        ExperienceEntity newEducation = experienceRepository.save(educationEntity);

        ExperienceDto educationDto = modelMapper.map(newEducation, ExperienceDto.class);

        return educationDto;
    }


    @Override
    public ExperienceDto getExperience(String experienceId) {

        ExperienceEntity educationEntity = experienceRepository.findByExperienceId(experienceId);

        ModelMapper modelMapper = new ModelMapper();

        ExperienceDto educationDto = modelMapper.map(educationEntity, ExperienceDto.class);

        return educationDto;
    }

    @Override
    public void deleteExperience(String experienceId) {

        ExperienceEntity education = experienceRepository.findByExperienceId(experienceId);

        if(education == null) throw new RuntimeException("education not found");

        experienceRepository.delete(education);

    }
    @Override
    public ExperienceDto updateExperience(String id, ExperienceDto educationDto) {

        ExperienceEntity educationEntity = experienceRepository.findByExperienceId(id);


        if(educationDto == null) throw new UsernameNotFoundException(id);
        educationEntity.setType(educationDto.getType());
        educationEntity.setDateDebut(educationDto.getDateDebut());
        educationEntity.setDateFin(educationDto.getDateFin());
        educationEntity.setJob_title(educationDto.getJob_title());
        educationEntity.setPays(educationDto.getPays());
        educationEntity.setOrganization(educationDto.getOrganization());

        ExperienceEntity educ = experienceRepository.save(educationEntity);

        ExperienceDto educt = new ExperienceDto();

        BeanUtils.copyProperties(educ, educt);

        return educt;
    }



}
