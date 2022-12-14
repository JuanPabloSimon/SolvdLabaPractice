package com.solvd.onlineshop.utils;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.shop.Cart;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Printer {
    private static Logger LOGGER = LogManager.getLogger(Printer.class);

    public static void printDivider() {
        LOGGER.info("-----------------------------");
    }

    public static void listProducts(ArrayList<Product> products) {
        for (Product prod : products) {
            LOGGER.info(prod);
        }
    }

    public static void printValues(Customer customer) throws EmptyLinkedListException {
        Double prodsValue = customer.getCurrency().changeOfCurrency(Calculator.totalProducts(customer.getProductsInCart()), customer.getCurrency().getName());
        Double deliveryValue = customer.getCurrency().changeOfCurrency(customer.getDelivery().getPrice(), customer.getCurrency().getName());
        Double total = customer.getCart().getPayment().applyDiscount(prodsValue + deliveryValue);
        LOGGER.info("Total Products Value: " + customer.getCurrency().getSymbol() + prodsValue.intValue());
        LOGGER.info("Delivery Value: " + customer.getCurrency().getSymbol() + deliveryValue.intValue());
        LOGGER.info("Total: " + customer.getCurrency().getSymbol() + total.intValue());
    }

}
