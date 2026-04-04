package com.vaishnavv.finance_dashboard.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



        @ExceptionHandler(ResourceNotFound.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public Map<String, Object> handleResourceNotFound(ResourceNotFound ex) {

            Map<String, Object> error = new HashMap<>();
            error.put("timestamp", LocalDateTime.now());
            error.put("status", 404);
            error.put("error", "Not Found");
            error.put("message", ex.getMessage());

            return error;
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public Map<String, Object> handleGenericException(Exception ex) {

            Map<String, Object> error = new HashMap<>();
            error.put("timestamp", LocalDateTime.now());
            error.put("status", 500);
            error.put("error", "Internal Server Error");
            error.put("message", ex.getMessage());

            return error;
        }
    }
    
