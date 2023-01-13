package com.example.example.answer.Dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class CreateAnswerDto {

    @NotBlank
    private String content;
    @NotBlank
    private String nickname;
    @Positive
    private Long questionId;
    @Positive
    private Long parentId;
}
