package com.solvd.onlineshop.people;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.shop.Cart;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.services.PaymentMethod;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.*;

import java.util.ArrayList;
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

    // methods section

    @Override
    public void selectProduct(Product product) throws OutOfStockException {
        if (product.getStock() > 0) {
            this.cart.addProduct(product);
        } else {
            String msg = String.format("Out of stock for product %s", product.getName());
            throw new OutOfStockException(msg);
        }
    }

    @Override
    public void deleteProduct(Product product) throws ElementNotFoundException, EmptyLinkedListException {
        this.cart.removeProduct(product.getProductID());
    }


    public void selectDelivery(DeliveryCompany delivery) {
        if (delivery.isAvailable()) {
            this.delivery = delivery;
        } else {
            throw new DeliveryNotAvailableException("Delivery not available, please select between possible options");
        }
    }

    public void selectPayment(PaymentMethod payment) {
        if (payment.isAvailable()) {
            this.cart.setPayment(payment);
        } else {
            throw new PaymentNotAvailableException("Payment not available, select between Credit or Debit");
        }
    }

    public void selectCurrency(Currency currency) {
        if (currency.isAvailable()) {
            this.currency = currency;
        } else {
            throw new CurrencyNotAvailableException("Currency not available, please check de possible options");
        }
    }


    // end of section

    // getters and setter section

    public void setInStore(boolean value) {
        this.isInStore = value;
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