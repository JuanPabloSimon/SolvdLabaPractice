package com.solvd.onlineshop.services;

import com.solvd.onlineshop.exceptions.CurrencyNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.ToDoubleBiFunction;

public class Currency {
    private static Logger LOGGER = LogManager.getLogger(Currency.class);
    private boolean available = true;
    private CurrencyType currency;


    // constructor section
    public Currency(CurrencyType currency) {
        switch (currency.name().toLowerCase()) {
            case "dolar": case "euro": case "ars": case "pounds":
                this.currency = currency;
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
    public Double changeOfCurrency(double initialValue,
                                   CurrencyType currencyFinal,
                                   ToDoubleBiFunction<Double, CurrencyType> changer) {
        Double finalValue = changer.applyAsDouble(initialValue, currencyFinal);
        return finalValue;
    }
    //end of section

    // getters and setters

    public String getName() {
        return this.currency.name();
    }


    public CurrencyType getCurrencyType() {
        return this.currency;
    }

    // end of section

    @Override
    public String toString() {
        return this.currency.name();
    }
}
