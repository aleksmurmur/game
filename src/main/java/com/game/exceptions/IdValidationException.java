package com.game.exceptions;

import org.springframework.http.HttpStatus;

public class IdValidationException extends BaseException{

    public final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public IdValidationException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

