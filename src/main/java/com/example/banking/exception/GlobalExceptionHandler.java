package com.example.banking.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiError make(int status, String error, String message, String path, Map<String,String> fields) {
        return new ApiError(Instant.now(), status, error, message, path, fields);
    }

    // NotFoundException
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFound(NotFoundException ex, HttpServletRequest req) {
        return make(404, "Not Found", ex.getMessage(), req.getRequestURI(), null);
    }

    // InsufficientFundsException
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError insufficient(InsufficientFundsException ex, HttpServletRequest req) {
        return make(400, "Bad Request", ex.getMessage(), req.getRequestURI(), null);
    }

    // Validation errors for bad input
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String,String> fields = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fields.put(fe.getField(), fe.getDefaultMessage());
        }
        return make(400, "Bad Request", "Validation failed", req.getRequestURI(), fields);
    }

    // Illegal arguments
    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError badReq(Exception ex, HttpServletRequest req) {
        return make(400, "Bad Request", ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError other(Exception ex, HttpServletRequest req) {
        return make(500, "Internal Server Error", ex.getMessage(), req.getRequestURI(), null);
    }

    // DTO
    public static class ApiError {
        public Instant timestamp;
        public int status;
        public String error;
        public String message;
        public String path;
        public Map<String,String> fieldErrors;
        public ApiError(Instant ts, int st, String er, String msg, String p, Map<String,String> fe) {
            this.timestamp = ts; this.status = st; this.error = er; this.message = msg; this.path = p; this.fieldErrors = fe;
        }
    }
}
