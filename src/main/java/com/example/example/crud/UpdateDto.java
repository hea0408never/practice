package com.example.example.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
}
