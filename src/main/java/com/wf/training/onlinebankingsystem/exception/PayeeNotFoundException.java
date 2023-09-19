package com.wf.training.onlinebankingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PayeeNotFoundException extends RuntimeException {
    public PayeeNotFoundException(String message) {
        super(message);
    }
}
