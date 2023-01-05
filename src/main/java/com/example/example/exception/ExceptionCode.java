package com.example.example.exception;

import lombok.Getter;

public enum ExceptionCode {
    QUESTION_NOT_FOUND(404,"질문을 찾을 수 없습니다."),
    ANSWER_NOT_FOUND(404,"답글을 찾을 수 없습니다.");

    @Getter
    private final int code;

    @Getter
    private final String message;

    ExceptionCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
