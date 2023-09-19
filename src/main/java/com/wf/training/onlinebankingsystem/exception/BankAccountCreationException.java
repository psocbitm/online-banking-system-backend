package com.wf.training.onlinebankingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BankAccountCreationException extends RuntimeException {
    public BankAccountCreationException(String message) {
        super(message);
    }
}
