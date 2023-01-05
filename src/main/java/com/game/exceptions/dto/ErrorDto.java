package com.game.exceptions.dto;

import java.util.List;

public class ErrorDto {

    private final String code;
    private final String message;


    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }



    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
