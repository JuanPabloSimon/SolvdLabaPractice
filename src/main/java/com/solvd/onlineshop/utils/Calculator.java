package com.solvd.onlineshop.utils;

import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Calculator {
    private static Logger LOGGER = LogManager.getLogger(Calculator.class);

    public static Integer totalProducts(CustomLinkedList<Product> products) throws EmptyLinkedListException {
        Integer totalProd = 0;
        ArrayList<Product> prods = products.getAll();

        for (Product prod : prods) {
            totalProd += prod.getPrice();
        }
        return totalProd;
    }

    public static Integer total(Customer customer) throws EmptyLinkedListException{
        Integer subTotal = totalProducts(customer.getProductsInCart());
        subTotal += customer.getDelivery().getPrice();
        Double value = customer.getCurrency().changeOfCurrency(subTotal, customer.getCurrency().getName());
        subTotal = value.intValue();

        if (customer.getCart().getPayment().getName() == "Debit") { // calculates a discount if it has to.
            int total = customer.getCart().getPayment().applyDiscount(subTotal.doubleValue()).intValue();
            return total;
        } else if (customer.getCart().getPayment().getName() == "Credit") {
            return subTotal.intValue();
        } else {
            LOGGER.warn("Please select a payment method between Credit and Debit");
            return 0;
        }
    }
}
