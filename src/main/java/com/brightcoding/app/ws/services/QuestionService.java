package com.brightcoding.app.ws.services;

import com.brightcoding.app.ws.shared.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> getAllQuestion(String email);

    QuestionDto createQuestion(QuestionDto question, String email);

    QuestionDto getQuestion(String questionId);
    QuestionDto updateQuestion(String Id, QuestionDto questionDto);
    void deleteQuestion(String questionId);
}
