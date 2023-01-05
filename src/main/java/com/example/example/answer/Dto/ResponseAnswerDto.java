package com.example.example.answer.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseAnswerDto {

    private String content;
    private String nickname;
    private LocalDateTime createdAt;
}
