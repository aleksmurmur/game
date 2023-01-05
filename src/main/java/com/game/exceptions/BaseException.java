package com.game.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException{

    public final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BaseException(String message) {
        super(message);
    }

}

