package com.example.example.answer.Mapper;

import com.example.example.answer.Dto.ResponseAnswerDto;
import com.example.example.answer.Entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    ResponseAnswerDto answerToResponseDto(Answer answer);
}
