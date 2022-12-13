package com.solvd.onlineshop.utils;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.PaymentMethod;
import com.solvd.onlineshop.shop.Cart;
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

    public static Integer total(Integer productsValue, Integer deliveryValue, PaymentMethod payMethod, Currency currency) {
        int subTotal = productsValue;

        subTotal += deliveryValue;
        LOGGER.info("Delivery Total: " + currency.getSymbol() + currency.changeOfCurrency(deliveryValue, currency.getName()));
        LOGGER.info("Subtotal: " + currency.getSymbol() + currency.changeOfCurrency(subTotal, currency.getName()));

        Double value = currency.changeOfCurrency(subTotal, currency.getName());
        subTotal = value.intValue();

        if (payMethod.getName() == "Debit") { // calculates a discount if it has to.
            int discount = payMethod.getDiscount();
            int total = subTotal - (subTotal / discount);
            LOGGER.info("Total value: " + currency.getSymbol() + total);
            return total;
        } else if (payMethod.getName() == "Credit") {
            LOGGER.info("Total value: " + currency.getSymbol() + subTotal);
            return subTotal;
        } else {
            LOGGER.warn("Please select a payment method between Credit and Debit");
            return 0;
        }
    }
}
