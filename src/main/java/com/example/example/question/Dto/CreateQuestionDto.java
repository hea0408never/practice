package com.example.example.question.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateQuestionDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String nickname;
}
