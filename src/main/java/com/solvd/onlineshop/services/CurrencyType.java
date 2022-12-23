package com.solvd.onlineshop.services;

public enum CurrencyType {
    DOLAR("$"),
    EURO("€"),
    ARS("ARS$"),
    POUNDS("£");

    private String currencySymbol;
    CurrencyType(String currencySymbol){
        this.currencySymbol = currencySymbol;
    };

    public String getCurrencySymbol() {
        return currencySymbol;
    }
}
