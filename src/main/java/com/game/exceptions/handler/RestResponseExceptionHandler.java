package com.game.exceptions.handler;

import com.game.exceptions.BaseException;
import com.game.exceptions.dto.ErrorDto;
import com.game.exceptions.FieldValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseExceptionHandler {

@ExceptionHandler(BaseException.class)
public ResponseEntity<ErrorDto> baseExceptionHandler(BaseException e, HttpServletRequest request) {
return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDto(e.getHttpStatus().name(), e.getMessage()));
}

@ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ErrorDto> handleFieldValidation(FieldValidationException e, HttpServletRequest request) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorDto(
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage()
            ));
}

}
