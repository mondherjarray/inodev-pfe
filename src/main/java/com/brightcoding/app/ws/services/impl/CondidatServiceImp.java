package com.brightcoding.app.ws.services.impl;

import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.entities.EducationEntity;
import com.brightcoding.app.ws.entities.UserEntity;
import com.brightcoding.app.ws.repositories.CondidatRepository;
import com.brightcoding.app.ws.repositories.UserRepository;
import com.brightcoding.app.ws.services.CondidatService;
import com.brightcoding.app.ws.shared.Utils;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.EducationDto;
import com.brightcoding.app.ws.shared.dto.UserDto;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CondidatServiceImp implements CondidatService {

    @Autowired
    CondidatRepository condidatRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    Utils util;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CondidatEntity condidatEntity = condidatRepository.findByEmail(email);

        if(condidatEntity == null) throw new UsernameNotFoundException(email);

        return new User(condidatEntity.getEmail(), condidatEntity.getEncryptedPassword(), new ArrayList<>());
    }
    @Override
    public CondidatDto getCondidatByUserId(String userId) {

        CondidatEntity condidatEntity = condidatRepository.findByUserId(userId);

        if(condidatEntity == null) throw new UsernameNotFoundException(userId);


        ModelMapper modelMapper = new ModelMapper();

        CondidatDto condidatDto = modelMapper.map(condidatEntity, CondidatDto.class);

        return condidatDto;

    }
    @Override
    public List<CondidatDto> getCondidat(int page, int limit, String search, int status) {

        if(page > 0) page = page - 1;

        List<CondidatDto> condidatsDto = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CondidatEntity> condidatPage;

        if(search.isEmpty()) {
            condidatPage = condidatRepository.findAllCondidats(pageableRequest);
        }
        else {

            condidatPage = condidatRepository.findAllCondidatByCriteria(pageableRequest, search, status);
        }


        List<CondidatEntity> condidats = condidatPage.getContent();

        for(CondidatEntity condidatEntity: condidats) {

            ModelMapper modelMapper = new ModelMapper();
            CondidatDto condidat = modelMapper.map(condidatEntity, CondidatDto.class);

            condidatsDto.add(condidat);
        }

        return condidatsDto;
    }
    @Override
    public CondidatDto updateCondidat(String id, CondidatEntity condidatDto) {

        CondidatEntity condidatEntity = condidatRepository.findByUserId(id);


        if(condidatEntity == null) throw new UsernameNotFoundException(id);
        condidatEntity.setFirstName(condidatDto.getFirstName());
        condidatEntity.setPhone(condidatDto.getPhone());
        condidatEntity.setLastName(condidatDto.getLastName());
        condidatEntity.setEmail(condidatDto.getEmail());
        condidatEntity.setDate_birthday(condidatDto.getDate_birthday());
        condidatEntity.setGender(condidatDto.getGender());
        condidatEntity.setNationality(condidatDto.getNationality());
        condidatEntity.setPhoto(condidatDto.getPhoto());
        CondidatEntity cond = condidatRepository.save(condidatEntity);

        CondidatDto con = new CondidatDto();

        BeanUtils.copyProperties(cond, con);

        return con;
    }
    @Override
    public CondidatDto createCondidat( CondidatDto condidat) {
        CondidatEntity checkUser = condidatRepository.findByEmail(condidat.getEmail());


        if (checkUser != null) throw new RuntimeException("User Alrady Exists !");

       /*for(int i=0; i < condidat.getEducation().size(); i++) {
        EducationDto education = condidat.getEducation().get(i);
        education.setCondidat(condidat);
        education.setEducationId(util.generateStringId(30));
        condidat.getEducation().set(i, education);
    }*/


        ModelMapper modelMapper = new ModelMapper();

        CondidatEntity condidatEntity = modelMapper.map(condidat, CondidatEntity.class);

        condidatEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(condidat.getPassword()));

        condidatEntity.setUserId(util.generateStringId(32));

        CondidatEntity newCondidat = condidatRepository.save(condidatEntity);

        CondidatDto condidatDto =  modelMapper.map(newCondidat, CondidatDto.class);

        return condidatDto;
    }
    @Override
    public void delete(String userId) {

        CondidatEntity userEntity = condidatRepository.findByUserId(userId);

        if(userEntity == null) throw new UsernameNotFoundException(userId);

        condidatRepository.delete(userEntity);

    }

}
