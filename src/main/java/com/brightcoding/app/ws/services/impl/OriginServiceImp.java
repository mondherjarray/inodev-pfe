package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.OriginEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.OriginRepository;
import com.brightcoding.app.ws.services.SourceService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.SourceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SourceServiceImp implements SourceService {
    @Autowired
    OriginRepository originRepository;

    @Autowired
    CondidatRepository condidatRepository;

    @Autowired
    Utils util;

    @Override
    public List<SourceDto> getAllSource(String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        List<OriginEntity> sources = currentCondidat.getAdmin() == true? (List<OriginEntity>) originRepository.findAll() : originRepository.findByCondidat(currentCondidat);

        Type listType = new TypeToken<List<OriginEntity>>() {}.getType();
        List<SourceDto> sourceDto = new ModelMapper().map(sources, listType);

        return sourceDto;
    }


    @Override
    public SourceDto createSource(SourceDto education, String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(currentCondidat, CondidatDto.class);
        education.setSourceId(util.generateStringId(30));
        education.setCondidat(condidatDto);

        OriginEntity educationEntity = modelMapper.map(education, OriginEntity.class);

        OriginEntity newEducation = originRepository.save(educationEntity);

        SourceDto educationDto = modelMapper.map(newEducation, SourceDto.class);

        return educationDto;
    }


    @Override
    public SourceDto getSource(String educationId) {

        OriginEntity educationEntity = originRepository.findBySourceId(educationId);

        ModelMapper modelMapper = new ModelMapper();

        SourceDto educationDto = modelMapper.map(educationEntity, SourceDto.class);

        return educationDto;
    }

    @Override
    public void deleteSource(String educationId) {

        OriginEntity education = originRepository.findBySourceId(educationId);

        if(education == null) throw new RuntimeException("education not found");

        originRepository.delete(education);

    }
    @Override
    public SourceDto updateSource(String id, SourceDto educationDto) {

        OriginEntity educationEntity = originRepository.findBySourceId(id);


        if(educationDto == null) throw new UsernameNotFoundException(id);
        educationEntity.setDescrib(educationDto.getDescrib());
        educationEntity.setPosition(educationDto.getPosition());

        OriginEntity educ = originRepository.save(educationEntity);

        SourceDto educt = new SourceDto();

        BeanUtils.copyProperties(educ, educt);

        return educt;
    }

}
