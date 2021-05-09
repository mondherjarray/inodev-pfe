package com.brightcoding.app.ws.services;

import com.brightcoding.app.ws.shared.dto.SourceDto;

import java.util.List;

public interface SourceService {
    List<SourceDto> getAllSource(String email);

    SourceDto createSource(SourceDto source, String email);

    SourceDto getSource(String sourceId);
    SourceDto updateSource(String Id, SourceDto sourceDto);
    void deleteSource(String sourceId);
}
