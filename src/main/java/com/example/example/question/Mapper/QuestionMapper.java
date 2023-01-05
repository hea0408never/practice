package com.example.example.question.Mapper;

import com.example.example.question.Dto.ResponseQuestionDto;
import com.example.example.question.Entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    ResponseQuestionDto questionToResponseDto(Question question);
}
