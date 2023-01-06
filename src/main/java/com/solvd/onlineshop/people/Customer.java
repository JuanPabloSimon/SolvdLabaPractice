package com.solvd.onlineshop.people;

import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.shop.Cart;
import com.solvd.onlineshop.shop.Order;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends Person {
    private static Logger LOGGER = LogManager.getLogger(Customer.class);
    private String username;
    private Cart cart;
    private boolean isInStore;
    private Order order;

    // constructor section
    public Customer(String username, String firstname, String lastname, Integer age, String email, String address) {
        super(firstname, lastname, age, email, address);
        this.username = username;
        this.cart = new Cart();
    }



    // end of section
    public  void createOrder() {
        this.order = new Order(this);
    }

    // getters and setter section

    public void setInStore(boolean value) {
        this.isInStore = value;
    }

    public void setDelivery(DeliveryCompany delivery) {
        this.order.setDeliveryCompany(delivery);
//        this.delivery = delivery; // delete this
    }

    public void setCurrency(Currency currency) {
        this.order.setCurrency(currency);
//        this.currency = currency; // delete this
    }

    public ArrayList<Product> getProductsInCart() {
        return this.cart.getProducts().getAll();
    }

    public Boolean getIsInStore() {
        return this.isInStore;
    }
    public String getUsername() {
        return this.username;
    }

    public Cart getCart() {
        return this.cart;
    }

    public Order getOrder() {
        return order;
    }

//    public DeliveryCompany getDelivery() {
//        return this.delivery;
//    }

//    public Currency getCurrency() {
//        return this.currency;
//    }

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
        return "Customer { " +
                "username='" + username + '\'' +
                ", cart products=" + getProductsInCart() +
                ", address='" + address + '\'' +
                '}';
    }
}