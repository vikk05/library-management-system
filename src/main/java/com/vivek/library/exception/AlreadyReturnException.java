package com.vivek.library.exception;

public class AlreadyReturnException extends RuntimeException {
    public AlreadyReturnException(String message) {
        super(message);
    }
}
