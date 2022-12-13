package com.solvd.onlineshop.exceptions;

public class CartEmptyException extends Exception {
    public CartEmptyException(String message) {
        super(message);
    }
}
