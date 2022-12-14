package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.services.PaymentMethod;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    private static Logger LOGGER = LogManager.getLogger(Order.class);
    private DeliveryCompany deliveryCompany;
    private PaymentMethod payMethod;
    private int totalValue;
    private ArrayList<Product> products;
    private Customer customer;
    private Currency currency;

    // constructor section

    public Order(Customer customer, DeliveryCompany deliveryCompany, PaymentMethod payMethod, int totalValue, ArrayList<Product> products, Currency currency) {
        this.deliveryCompany = deliveryCompany;
        this.customer = customer;
        this.payMethod = payMethod;
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
                + payMethod + "\nCurrency selected: " + this.currency.getName() + "\nTotal Value in the selected currency: " + currency.getSymbol() + totalValue;
    }


}
