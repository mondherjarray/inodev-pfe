package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.SkillsEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.SkillsRepository;
import com.brightcoding.app.ws.services.SkillsService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.SkillsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SkillsServiceImp implements SkillsService {

    @Autowired
    SkillsRepository educationRepository;

    @Autowired
    CondidatRepository condidatRepository;

    @Autowired
    Utils util;

    @Override
    public List<SkillsDto> getAllSkills(String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);
        List<SkillsEntity> educations;
if( currentCondidat.getAdmin() == 0) {
    educations = educationRepository.findByCondidat(currentCondidat);
}
else if (currentCondidat.getAdmin() == 1){
    educations =  (List<SkillsEntity>) educationRepository.findAll();
}
else if (currentCondidat.getAdmin() == 2){
    educations =  (List<SkillsEntity>) educationRepository.findAll();
}
else {
return null; }
        Type listType = new TypeToken<List<SkillsEntity>>() {}.getType();
        List<SkillsDto> educationsDto = new ModelMapper().map(educations, listType);

        return educationsDto;
    }


    @Override
    public SkillsDto createSkills(SkillsDto education, String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(currentCondidat, CondidatDto.class);
        education.setSkillsId(util.generateStringId(30));
        education.setCondidat(condidatDto);

        SkillsEntity educationEntity = modelMapper.map(education, SkillsEntity.class);

        SkillsEntity newEducation = educationRepository.save(educationEntity);

        SkillsDto educationDto = modelMapper.map(newEducation, SkillsDto.class);

        return educationDto;
    }


    @Override
    public SkillsDto getSkills(String educationId) {

        SkillsEntity educationEntity = educationRepository.findBySkillsId(educationId);

        ModelMapper modelMapper = new ModelMapper();

        SkillsDto educationDto = modelMapper.map(educationEntity, SkillsDto.class);

        return educationDto;
    }

    @Override
    public void deleteSkills(String educationId) {

        SkillsEntity education = educationRepository.findBySkillsId(educationId);

        if(education == null) throw new RuntimeException("education not found");

        educationRepository.delete(education);

    }
    @Override
    public SkillsDto updateSkills(String id, SkillsDto educationDto) {

        SkillsEntity educationEntity = educationRepository.findBySkillsId(id);


        if(educationDto == null) throw new UsernameNotFoundException(id);
        educationEntity.setNiveau(educationDto.getNiveau());
        educationEntity.setNom(educationDto.getNom());
        educationEntity.setCertifier(educationDto.getCertifier());
        educationEntity.setNiveau(educationDto.getNiveau());
        educationEntity.setCertifier(educationDto.getCertifier());
        SkillsEntity educ = educationRepository.save(educationEntity);

        SkillsDto educt = new SkillsDto();

        BeanUtils.copyProperties(educ, educt);

        return educt;
    }


}
