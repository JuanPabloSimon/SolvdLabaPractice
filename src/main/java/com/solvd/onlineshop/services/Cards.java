package com.solvd.onlineshop.services;

public enum Cards {
    VISA(0,"credit"),
    MASTERCARD(10, "debit"),
    AMEX(5, "credit"),
    DISCOVER(10, "debit"),
    AMERICAN_EXPRESS(20, "debit");

    private Integer discount;
    private String type;
    Cards(Integer discount, String type){
        this.discount = discount;
        this.type = type;
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Card: " + name() +
                ", discount: " + discount +
                ", type: '" + type + '\'';
    }
}
