package com.example.example.question.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseQuestionDto {

    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
}
