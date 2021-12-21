package com.ms.order.application.exceptions;

public class InvalidAmountProductException extends  Exception {
    public InvalidAmountProductException() {
        super("amount is required");
    }
}
