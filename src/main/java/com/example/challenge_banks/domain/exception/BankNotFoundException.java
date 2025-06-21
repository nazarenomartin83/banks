package com.example.challenge_banks.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(long id) {
        super("Bank with ID " + id + " not found.");
    }
}
