package org.example.testtask.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<String> handleException(Exception e) {

        LOGGER.error(e.getMessage(), e);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}