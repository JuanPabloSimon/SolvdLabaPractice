package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.exceptions.PaymentNotAvailableException;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Cards;
import com.solvd.onlineshop.services.Payment;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class Cart {
    private static Logger LOGGER = LogManager.getLogger(Cart.class);
    private CustomLinkedList<Product> productsInCart = new CustomLinkedList<>();
    private final int cartId;
    private Payment payment;
    private Cards card;
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

    public void selectPayment(Payment payment) {
        if (payment.isAvailable()) {
            setPayment(payment);
        } else {
            throw new PaymentNotAvailableException("Payment not available, select between Credit or Debit");
        }
    }

    public void selectCard(String card) {
        this.card = payment.getCard(card);
    }

    // getters and setters methods
    public CustomLinkedList<Product> getProducts() {
        return productsInCart;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public Cards getCard( ){
        return this.card;
    }
    // end of section


    @Override
    public String toString() {
        return "Cart{" +
                "productsInCart=" + productsInCart +
                ", cartId=" + cartId +
                ", payment=" + payment +
                ", card=" + card +
                ", rand=" + rand +
                '}';
    }
}

