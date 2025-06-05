package com.pablodll.country_api_service.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Global exception handler for REST Controller.
 * Catches exceptions and processes them into HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors thrown when a @Valid annotated request fails.
     *
     * @param ex the MethodArgumentNotValidException
     * @return a response with 400 BAD REQUEST and validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("Field '%s': %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>("Error: " + errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles data integrity violation errors thrown by the database
     *
     * @param ex the DataIntegrityViolationException
     * @return a response with 409 CONFLICT
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleException(DataIntegrityViolationException ex) {
        String errorMessage = "The request could not be completed due to a database constraint violation.";
        return new ResponseEntity<>("Error: " + errorMessage, HttpStatus.CONFLICT);
    }

    /**
     * Handles unexpected exceptions.
     *
     * @param ex the Exception
     * @return a response with 500 INTERNAL SERVER ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>("Unexpected Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
