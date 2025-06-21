package com.example.challenge_banks.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // This annotation makes Spring return a 409 Conflict status
public class BankAlreadyExistsException extends RuntimeException {
    public BankAlreadyExistsException(long id) {
        super("Bank with ID " + id + " already exists.");
    }
}