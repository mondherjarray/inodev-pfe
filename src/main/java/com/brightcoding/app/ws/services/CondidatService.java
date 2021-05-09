package com.brightcoding.app.ws.services;

import java.util.List;


import com.brightcoding.app.ws.entities.CondidatEntity;
import com.brightcoding.app.ws.shared.dto.CondidatDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CondidatService {
    CondidatDto createCondidat(CondidatDto condidat) ;

    List<CondidatDto> getCondidat(int page, int limit, String search, int status);
    CondidatDto updateCondidat(String Id, CondidatEntity a);
    CondidatDto getCondidatByUserId(String userId);
    UserDetails loadUserByUsername(String email);
    void delete(String userId);
}
