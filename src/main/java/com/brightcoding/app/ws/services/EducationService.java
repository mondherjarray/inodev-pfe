package com.brightcoding.app.ws.services;

import com.brightcoding.app.ws.shared.dto.CondidatDto;
import com.brightcoding.app.ws.shared.dto.EducationDto;

import java.util.List;

public interface EducationService {
    List<EducationDto> getAllEducation(String email);

    EducationDto createEducation(EducationDto education, String email);

    EducationDto getEducation(String educationId);
    EducationDto updateEducation(String Id, EducationDto educationDto);
    void deleteEducation(String educationId);
}
