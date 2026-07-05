package com.vivek.library.exception;

public class DuplicateBorrowException extends RuntimeException {
    public DuplicateBorrowException(String message) {
        super(message);
    }
}
