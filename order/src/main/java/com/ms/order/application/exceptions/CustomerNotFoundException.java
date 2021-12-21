package com.ms.order.application.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("The customer id %s not found", id));
    }
}
