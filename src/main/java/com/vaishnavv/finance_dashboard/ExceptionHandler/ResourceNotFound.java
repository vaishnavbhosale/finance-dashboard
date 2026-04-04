package com.vaishnavv.finance_dashboard.ExceptionHandler;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}



