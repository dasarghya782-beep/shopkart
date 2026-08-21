package com.shopkart.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long customerId){
        super("Customer does not exist with id: " + customerId);
    }
}
