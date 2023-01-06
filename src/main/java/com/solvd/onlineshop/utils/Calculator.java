package com.solvd.onlineshop.utils;

import com.solvd.onlineshop.exceptions.CurrencyNotAvailableException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.lambdas.Discountable;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Cards;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private static Logger LOGGER = LogManager.getLogger(Calculator.class);

    public static Integer totalProducts(ArrayList<Product> products) throws EmptyLinkedListException {
        Integer totalProd = 0;
        ArrayList<Product> prods = products;

        for (Product prod : prods) {
            totalProd += prod.getPrice();
        }
        return totalProd;
    }

    public static Integer total(Customer customer, Discountable<Double, Cards> discounter) throws EmptyLinkedListException{
        Integer subTotal = totalProducts(customer.getProductsInCart());
        subTotal += customer.getOrder().getDeliveryCompany().getPrice();
        Double value = customer.getOrder().getCurrency().changeOfCurrency(
                subTotal,
                customer.getOrder().getCurrency().getCurrencyType(),
                (v, currency) -> {
                    switch (currency.name().toLowerCase()) {
                        case "dolar":
                            return v;
                        case "euro":
                            return v * 0.98;
                        case "ars":
                            return v * 200.18;
                        case "pounds":
                            return v * 0.85;
                        default:
                            throw new CurrencyNotAvailableException("Currency not available");
                    }
                });
        subTotal = value.intValue();

        int total = discounter.apply(subTotal.doubleValue(), customer.getCart().getCard()).intValue();
        return total;
    }
}
