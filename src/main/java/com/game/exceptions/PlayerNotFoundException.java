package com.game.exceptions;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends BaseException{

     public final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public PlayerNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
