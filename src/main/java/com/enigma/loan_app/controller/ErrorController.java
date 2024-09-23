package com.enigma.loan_app.controller;

import com.enigma.loan_app.dto.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseStatusException(ResponseStatusException e) {
        CommonResponse<?> response = CommonResponse.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseStatusException constraintViolationException(ConstraintViolationException e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getConstraintViolations().iterator().next().getMessage());

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseStatusException usernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseStatusException httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseStatusException exception(Exception e) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
