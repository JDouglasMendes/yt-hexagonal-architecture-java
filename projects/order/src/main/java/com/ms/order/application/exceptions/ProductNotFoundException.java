package com.ms.order.application.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super(String.format("Products not found"));
    }
}
