package com.wf.training.onlinebankingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatePayeeException extends RuntimeException {
    public DuplicatePayeeException(String message) {
        super(message);
    }
}

