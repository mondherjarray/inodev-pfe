package com.brightcoding.app.ws.services;

import com.brightcoding.app.ws.shared.dto.SkillsDto;

import java.util.List;

public interface SkillsService {
    List<SkillsDto> getAllSkills(String email);

    SkillsDto createSkills(SkillsDto skills, String email);

    SkillsDto getSkills(String skillsId);
    SkillsDto updateSkills(String Id, SkillsDto skillsDto);
    void deleteSkills(String skillsId);
}
