package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.QuestionEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.QuestionRepository;
import com.brightcoding.app.ws.services.QuestionService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    QuestionRepository educationRepository;

    @Autowired
    CondidatRepository condidatRepository;

    @Autowired
    Utils util;

    @Override
    public List<QuestionDto> getAllQuestion(String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        List<QuestionEntity> educations = currentCondidat.getAdmin() == 1? (List<QuestionEntity>) educationRepository.findAll() : educationRepository.findByCondidat(currentCondidat);

        Type listType = new TypeToken<List<QuestionEntity>>() {}.getType();
        List<QuestionDto> educationsDto = new ModelMapper().map(educations, listType);

        return educationsDto;
    }


    @Override
    public QuestionDto createQuestion(QuestionDto education, String email) {

        CondidatEntity currentCondidat = condidatRepository.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        CondidatDto condidatDto = modelMapper.map(currentCondidat, CondidatDto.class);
        education.setQuestionId(util.generateStringId(30));
        education.setCondidat(condidatDto);

        QuestionEntity educationEntity = modelMapper.map(education, QuestionEntity.class);

        QuestionEntity newEducation = educationRepository.save(educationEntity);

        QuestionDto educationDto = modelMapper.map(newEducation, QuestionDto.class);

        return educationDto;
    }


    @Override
    public QuestionDto getQuestion(String educationId) {

        QuestionEntity educationEntity = educationRepository.findByQuestionId(educationId);

        ModelMapper modelMapper = new ModelMapper();

        QuestionDto educationDto = modelMapper.map(educationEntity, QuestionDto.class);

        return educationDto;
    }

    @Override
    public void deleteQuestion(String educationId) {

        QuestionEntity education = educationRepository.findByQuestionId(educationId);

        if(education == null) throw new RuntimeException("education not found");

        educationRepository.delete(education);

    }
    @Override
    public QuestionDto updateQuestion(String id, QuestionDto educationDto) {

        QuestionEntity educationEntity = educationRepository.findByQuestionId(id);


        if(educationDto == null) throw new UsernameNotFoundException(id);
        educationEntity.setPremier(educationDto.getPremier());
        educationEntity.setDeuxieme(educationDto.getDeuxieme());
        educationEntity.setTroiseme(educationDto.getTroiseme());

        QuestionEntity educ = educationRepository.save(educationEntity);

        QuestionDto educt = new QuestionDto();

        BeanUtils.copyProperties(educ, educt);

        return educt;
    }



}
