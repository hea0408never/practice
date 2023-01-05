package com.example.example.question.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateQuestionDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
}
