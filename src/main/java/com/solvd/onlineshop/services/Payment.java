package com.solvd.onlineshop.services;

import com.solvd.onlineshop.exceptions.PaymentNotAvailableException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Payment {
    private static Logger LOGGER = LogManager.getLogger(Payment.class);
    private String method;
    private ArrayList<Cards> paymentMethods;
    private boolean available = true;

    // constructor section
    public Payment(String method) {
        switch (method.toLowerCase()) {
            case "debit":
                this.method = "debit";
                paymentMethods = Arrays.stream(Cards.values())
                        .filter(p -> p.getType().equals("debit"))
                        .collect(Collectors.toCollection(ArrayList<Cards>::new));
                break;
            case "credit":
                this.method = "credit";
                paymentMethods = Arrays.stream(Cards.values())
                        .filter(p -> p.getDiscount().equals("credit"))
                        .collect(Collectors.toCollection(ArrayList<Cards>::new));
                break;
            default:
                throw new PaymentNotAvailableException("Payment not available, select between credit and debit");
        }
    }
    // end of section

    // methods section


    public boolean isAvailable() throws PaymentNotAvailableException {
        return this.available;
    }

    // end of section

    // getters end set-ers
    public void printPaymentMethods() {
        for (Cards payment : paymentMethods) {
            LOGGER.info(payment.name());
        }
    }
    public ArrayList<Cards> getPaymentMethods() {
        return this.paymentMethods;
    }
    public Cards getCard(String card) {
        return paymentMethods.stream()
                .filter(x -> x.name().toLowerCase().equals(card.toLowerCase()))
                .findAny().orElse(null);
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }

    // end of section


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment that = (Payment) o;
        return method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method);
    }

    @Override
    public String toString() {
        return "Payment Method: " + this.method + " - possible Cards:" + getPaymentMethods().toString()  + "%";
    }

}
