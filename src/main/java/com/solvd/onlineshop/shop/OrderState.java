package com.solvd.onlineshop.shop;

public enum OrderState {
    PENDING("pending"),
    PROCESSING("processing"),
    COMPLETED("completed"),
    CANCELLED("cancelled"),
    FAILED("failed");

    private String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
