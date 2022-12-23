package com.solvd.onlineshop.utils;

import com.solvd.onlineshop.exceptions.CurrencyNotAvailableException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.lambdas.Discountable;
import com.solvd.onlineshop.lambdas.Printable;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Cards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Printer {
    private static Logger LOGGER = LogManager.getLogger(Printer.class);

    public static void printDivider() {
        LOGGER.info("-----------------------------");
    }

    public static void listProducts(List<Product> products, Printable<List> printer) {
        printer.print(products);
    }

    public static void printValues(Customer customer, Discountable<Double, Cards> discounter) throws EmptyLinkedListException {
        Double prodsValue = customer.getCurrency().changeOfCurrency(Calculator.totalProducts(customer.getProductsInCart()),
                customer.getCurrency().getCurrencyType(),
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
        Double deliveryValue = customer.getCurrency().changeOfCurrency(customer.getDelivery().getPrice(),
                customer.getCurrency().getCurrencyType(),
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
        Double total = discounter.apply(prodsValue + deliveryValue, customer.getCart().getCard());
        LOGGER.info("Total Products Value: " + customer.getCurrency().getCurrencyType().getCurrencySymbol() + prodsValue.intValue());
        LOGGER.info("Delivery Value: " + customer.getCurrency().getCurrencyType().getCurrencySymbol() + deliveryValue.intValue());
        LOGGER.info("Total: " + customer.getCurrency().getCurrencyType().getCurrencySymbol() + total.intValue());
    }

}
