package com.solvd.onlineshop.utils;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.shop.Cart;
import com.solvd.onlineshop.utils.customlinkedlist.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

}
