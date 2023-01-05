package com.game.exceptions;

import org.springframework.http.HttpStatus;

public class FieldValidationException extends BaseException{

    public final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public FieldValidationException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
