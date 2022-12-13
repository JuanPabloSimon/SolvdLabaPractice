package com.solvd.onlineshop.exceptions;

public class PaymentNotAvailableException extends RuntimeException {
    public PaymentNotAvailableException(String message) {
        super(message);
    }
}
