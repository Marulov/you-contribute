package com.marulov.youcontribute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(DuplicatedProjectException.class)
    public ResponseEntity<?> duplicatedProjectException(DuplicatedProjectException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}