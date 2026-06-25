package com.vivek.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleBookNotFound(BookNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCategoryNotFound(CategoryNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
