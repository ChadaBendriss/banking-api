package com.example.banking.exception;

public class NotFoundException extends RuntimeException {

    // Constructor
    public NotFoundException(String message) { 
        super(message); 
    }
}
