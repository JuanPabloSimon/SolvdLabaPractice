package com.solvd.onlineshop.services;

import com.solvd.onlineshop.exceptions.PaymentNotAvailableException;
import org.apache.logging.log4j.*;

import java.util.Objects;

public class PaymentMethod {
    private static Logger LOGGER = LogManager.getLogger(PaymentMethod.class);
    private String method;
    private int discount;
    private boolean available = true;

    // constructor section
    public PaymentMethod(String method) {
        this.method = method;
        if (method == "Debit") {
            this.discount = 10;
        } else if (method == "Credit") {
            this.discount = 0;
        } else {
            LOGGER.warn("Please select Debit or Credit");
            this.available = false;
        }
    }
    // end of section

    // methods section

    public boolean isAvailable() throws PaymentNotAvailableException {
        return this.available;
    }

    // end of section

    // getters end setters

    public int getDiscount() {
        return this.discount;
    }

    public String getName() {
        return this.method;
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }

    // end of section


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method);
    }

    @Override
    public String toString() {
        return "Payment Method: " + this.method + " - discount: " +
                this.discount + "%";
    }
}
