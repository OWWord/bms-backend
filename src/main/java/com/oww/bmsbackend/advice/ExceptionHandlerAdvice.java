package com.oww.bmsbackend.advice;

import com.oww.bmsbackend.exception.NotAcceptableException;
import com.oww.bmsbackend.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundHandler(NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<String> notAcceptableHandler(NotAcceptableException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

}
