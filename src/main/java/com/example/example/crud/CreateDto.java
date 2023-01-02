package com.example.example.crud;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String nickname;
}
