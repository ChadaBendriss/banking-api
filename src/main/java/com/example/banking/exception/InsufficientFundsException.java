package com.example.banking.exception;

public class InsufficientFundsException extends RuntimeException {

    // Constructor
    public InsufficientFundsException(String message) { 
        super(message); 
    }
}
