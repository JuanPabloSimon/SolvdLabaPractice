package com.solvd.onlineshop.services;

import com.solvd.onlineshop.exceptions.DeliveryNotAvailableException;
import org.apache.logging.log4j.*;

import java.util.Objects;

public class DeliveryCompany {
    private static Logger LOGGER = LogManager.getLogger(DeliveryCompany.class);
    private String name;
    private int price;
    private int timeToDeliver;
    private boolean available = true;

    // constructor section

    public DeliveryCompany(String name, int price, int timeToDeliver) {
        this.name = name;
        this.price = price;
        this.timeToDeliver = timeToDeliver;
    }

    // end section

    // methods section

    public boolean isAvailable() throws DeliveryNotAvailableException {
        return this.available;
    }

    // end of section

    // getters and setters
    public Integer getPrice() {
        return this.price;
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }


    // end of section


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryCompany that = (DeliveryCompany) o;
        return price == that.price && timeToDeliver == that.timeToDeliver && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, timeToDeliver);
    }

    @Override
    public String toString() {
        return "Delivery Name: " + this.name + " - price: $"
                + this.price + " - Time to arrive: "
                + this.timeToDeliver + " days.";
    }
}
