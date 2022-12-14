package com.solvd.onlineshop.people;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.shop.Cart;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.*;

import java.util.Objects;

public class Customer extends Person {
    private static Logger LOGGER = LogManager.getLogger(Customer.class);
    private String username;
    private Cart cart;
    private boolean isInStore;
    private DeliveryCompany delivery;
    private Currency currency;

    // constructor section
    public Customer(String username, String firstname, String lastname, int age, String email, String address) {
        super(firstname, lastname, age, email, address);
        this.username = username;
        this.cart = new Cart();
    }


    // end of section

    // getters and setter section

    public void setInStore(boolean value) {
        this.isInStore = value;
    }

    public void setDelivery(DeliveryCompany delivery) {
        this.delivery = delivery;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public CustomLinkedList<Product> getProductsInCart() {
        return this.cart.getProducts();
    }

    public String getUsername() {
        return this.username;
    }

    public Cart getCart() {
        return this.cart;
    }

    public DeliveryCompany getDelivery() {
        return this.delivery;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    // end of section

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return username.equals(customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "username='" + username + '\'' +
                ", cart products=" + cart +
                ", address='" + address + '\'' +
                '}';
    }
}