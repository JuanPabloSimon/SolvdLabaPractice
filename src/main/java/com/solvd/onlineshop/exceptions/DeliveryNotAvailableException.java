package com.solvd.onlineshop.exceptions;

public class DeliveryNotAvailableException extends RuntimeException {
    public DeliveryNotAvailableException(String message) {
        super(message);
    }
}
