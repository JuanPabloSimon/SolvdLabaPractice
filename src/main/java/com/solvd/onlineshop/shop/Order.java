package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Cards;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Order {
    private static Logger LOGGER = LogManager.getLogger(Order.class);
    private DeliveryCompany deliveryCompany;
    private Cards cardSelected;
    private int totalValue;
    private ArrayList<Product> products;
    private Customer customer;
    private Currency currency;

    // constructor section

    public Order(Customer customer, DeliveryCompany deliveryCompany, Cards card, int totalValue, ArrayList<Product> products, Currency currency) {
        this.deliveryCompany = deliveryCompany;
        this.customer = customer;
        this.cardSelected = card;
        this.totalValue = totalValue;
        this.products = products;
        this.currency = currency;
    }

    // end of section

    // methods section


    // end of section

    // getters and setters


    public int getTotal() {
        return this.totalValue;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    // end of section

    @Override
    public String toString() {
        return "Costumer Name: " + customer.getName() + "\nCostumer address: " + customer.getAddress()
                + "\nProducts: " + products + "\nDelivery Company: " + deliveryCompany + "\nPayment: "
                + cardSelected + "\nCurrency selected: " + this.currency.getName() + "\nTotal Value in the selected currency: " + currency.getCurrencyType().getCurrencySymbol() + totalValue;
    }


}
