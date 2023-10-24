package com.julian.restfulapi.error.local;

public class LocalNotFoundException extends RuntimeException{
    public LocalNotFoundException(String message) {
        super(message);
    }
}
