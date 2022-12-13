package com.solvd.onlineshop.exceptions;

public class CurrencyNotAvailableException extends RuntimeException {
    public CurrencyNotAvailableException(String message) {
        super(message);
    }
}
