package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.dto.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
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
    public void constraintViolationException(ConstraintViolationException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void usernameNotFoundException(UsernameNotFoundException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
