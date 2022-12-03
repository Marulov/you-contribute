package com.marulov.youcontribute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedProjectException extends RuntimeException {

    public DuplicatedProjectException(String message) {
        super(message);
    }
}