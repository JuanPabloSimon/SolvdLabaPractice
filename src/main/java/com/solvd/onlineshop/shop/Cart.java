package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.exceptions.PaymentNotAvailableException;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.PaymentMethod;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.*;

import java.util.Random;


public class Cart {
    private static Logger LOGGER = LogManager.getLogger(Cart.class);
    private CustomLinkedList<Product> productsInCart = new CustomLinkedList<>();
    private final int cartId;
    private PaymentMethod payment;
    Random rand = new Random();

    // constructor section
    public Cart() {
        this.cartId = rand.nextInt(1000);
    }

    // end section


    // methods section

    public void addProduct(Product product) {
        productsInCart.addElementAtStart(product);
    }

    public void removeProduct(Integer id) throws EmptyLinkedListException, ElementNotFoundException {

        boolean containsProduct = false;
        for (int i = 0; i < productsInCart.getSize(); i++) {
            if (productsInCart.get(i).getProductID().equals(id)) {
                productsInCart.deleteAt(productsInCart.get(i).getProductID());
                containsProduct = true;
            }
        }
        if (!containsProduct) {
            throw new ElementNotFoundException("Product not found.");
        }
    }

    public void selectPayment(PaymentMethod payment) {
        if (payment.isAvailable()) {
            setPayment(payment);
        } else {
            throw new PaymentNotAvailableException("Payment not available, select between Credit or Debit");
        }
    }

    // getters and setters methods
    public CustomLinkedList<Product> getProducts() {
        return productsInCart;
    }

    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    public PaymentMethod getPayment() {
        return this.payment;
    }
    // end of section
}

