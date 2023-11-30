package com.julian.restfulapi.error.local;

public class ManagerNotFoundException extends RuntimeException{

    public ManagerNotFoundException(String message) {
        super(message);
    }
}
