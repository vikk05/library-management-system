package com.vivek.library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException (String message){
        super(message);
    }

}


