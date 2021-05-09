package com.brightcoding.app.ws.services;

import com.brightcoding.app.ws.shared.dto.OriginDto;

import java.util.List;

public interface OriginService {
    List<OriginDto> getAllSource(String email);

    OriginDto createSource(OriginDto source, String email);

    OriginDto getSource(String sourceId);
    OriginDto updateSource(String Id, OriginDto originDto);
    void deleteSource(String sourceId);
}
