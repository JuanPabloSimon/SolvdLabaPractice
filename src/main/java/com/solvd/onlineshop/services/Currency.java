package com.solvd.onlineshop.services;

import com.solvd.onlineshop.exceptions.CurrencyNotAvailableException;
import org.apache.logging.log4j.*;

public class Currency {
    private static Logger LOGGER = LogManager.getLogger(Currency.class);
    private String name;
    private boolean available = true;
    private String currencySymbol;


    // constructor section
    public Currency(String name) {
        this.name = name;

        switch (name) {
            case "Dolar":
                this.currencySymbol = "$";
                break;
            case "Euro":
                this.currencySymbol = "€";
                break;
            case "ARS":
                this.currencySymbol = "ARS$";
                break;
            case "Pounds":
                this.currencySymbol = "£";
                break;
            default:
                LOGGER.warn("Pleas select a valid currency \nOptions: Dolar, Euro, ARS, Pounds");
                this.available = false;
                break;
        }

    }
    // end of section

    // methods section

    public boolean isAvailable() throws CurrencyNotAvailableException {
        return this.available;
    }

    public Double changeOfCurrency(double initialValue, String currencyFinal) {
        switch (currencyFinal) {
            case "Dolar":
                return initialValue;
            case "Euro":
                return initialValue * 0.98;
            case "ARS":
                return initialValue * 163.18;
            case "Pounds":
                return initialValue * 0.85;
            default:
                return initialValue;
        }
    }

    //end of section

    // getters and setters

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.currencySymbol;
    }

    // end of section

    @Override
    public String toString() {
        return this.name;
    }
}
