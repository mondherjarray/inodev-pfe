package com.brightcoding.app.ws.services;

import com.brightcoding.app.ws.shared.dto.ExperienceDto;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDto> getAllExperience(String email);

    ExperienceDto createExperience(ExperienceDto experience, String email);

    ExperienceDto getExperience(String experienceId);
    ExperienceDto updateExperience(String Id, ExperienceDto experienceDto);
    void deleteExperience(String experienceId);
}
