package com.example.example.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {

    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
}
