package com.julian.restfulapi.error.local;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id) {
        super("Customer con ID: " + id + " no fue encontrado");
    }
}
