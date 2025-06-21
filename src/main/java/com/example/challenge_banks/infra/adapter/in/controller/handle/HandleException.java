package com.example.challenge_banks.infra.adapter.in.controller.handle;

import com.example.challenge_banks.domain.exception.BankNotFoundException;
import com.example.challenge_banks.domain.exception.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<ExceptionResult> handleException(BankNotFoundException exception)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResult(exception.getMessage()));
    }
}
