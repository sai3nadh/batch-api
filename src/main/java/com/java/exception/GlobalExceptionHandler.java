package com.java.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(InvalidDataAccessApiUsageException.class)
	    public ResponseEntity<String> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
	        // Custom error message
	        String errorMessage = "Invalid usage of Data Access API";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	    }
}
