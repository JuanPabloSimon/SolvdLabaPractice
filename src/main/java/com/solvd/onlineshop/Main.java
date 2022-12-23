package com.solvd.onlineshop;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.CurrencyType;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.services.Payment;
import com.solvd.onlineshop.shop.OnlineShop;
import com.solvd.onlineshop.utils.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Products instantiation
        Product product1 = new Product("Iphone 14", 1500, "Some description", "SmartPhones", 0);
        Product product2 = new Product("Iphone 13", 1100, "Some description", "SmartPhones", 1);
        Product product3 = new Product("Iphone 12", 900, "Some description", "SmartPhones", 2);
        Product product4 = new Product("Iphone SE", 700, "Some description", "SmartPhones", 3);

        // Shop instantiation

        OnlineShop shop = new OnlineShop("Apple", "Technology Shop");


        shop.modifyProduct(product1, (prod, shopProds) -> {
            shopProds.add(prod);
        });
        shop.modifyProduct(product2, (prod, shopProds) -> {
            shopProds.add(prod);
        });
        shop.modifyProduct(product3, (prod, shopProds) -> {
            shopProds.add(prod);
        });

        shop.incrementStock(10, (prod, stock) -> {
            for (Product p : prod) {
                p.setStock(stock);
            }
        });

        shop.incrementStock(product1,30, (prod, stock) -> {
            prod.setStock(30);
        });
        LOGGER.info(shop.getName());
        LOGGER.info("Products of the store: ");
        Printer.listProducts(shop.getShopProducts(),
                (prods) -> {
                    for (Object prod : prods) {
                        LOGGER.info(prod);
                    }
                } );

        LOGGER.info("Products of the store Filtered: ");
        Printer.listProducts(shop.filterProdByPrice(1100.00, 900.00),
                (prods) -> {
                    for (Object prod : prods) {
                        LOGGER.info(prod);
                    }
                });

        Printer.printDivider();

        // Costumer instantiation

        Customer customer = new Customer("John_123", "John", "Smith", 22, "john@mail.com", "Street 1 345");
        Customer customer2 = new Customer("JuanS", "Juan", "Simon", 21, "jsimon@mail.com", "Street 16 345");

        shop.signUpCostumer(customer); // costumer logged in the page



       LOGGER.info("Product Selected: ");
        try {
            shop.orderProduct(customer, "Iphone 14");
            shop.orderProduct(customer, "Iphone 13");// two products selected from the store
            shop.deleteProduct(customer, "Iphone 13");
            Printer.listProducts(shop.getCustomerProducts(customer),
                    (prods) -> {
                        for (Object prod : prods) {
                            LOGGER.info(prod);
                        }
                    } );
        } catch (ProductNotFoundException | OutOfStockException | EmptyLinkedListException |
                 ElementNotFoundException e) {
            LOGGER.error(e);
        }


        DeliveryCompany delivery = new DeliveryCompany("Andreani", 10, 2);
        DeliveryCompany delivery2 = new DeliveryCompany("Correo Argentino", 8, 5);
        Payment payment = new Payment("Debit");
        Payment payment2 = new Payment("Credit");
        Currency currency = new Currency(CurrencyType.ARS);

        // selection of services
        shop.selectDelivery(customer, delivery);// Delivery Company Selected
        LOGGER.info(customer.getDelivery());
        shop.selectPayment(customer, payment); // Payment Method Selected
        shop.selectCard(customer, "mastercard");
        LOGGER.info(customer.getCart().getCard());
        shop.selectCurrency(customer, currency); // Currency selected
        LOGGER.info("Currency selected: " + customer.getCurrency());

        Printer.printDivider();
        LOGGER.info("Final Values");


        try {
            int total = shop.getTotalPrice(customer); // Checks the values and return a total cost
            Printer.printValues(customer,
                    (amount, card) -> amount - (amount / card.getDiscount()));
            Printer.printDivider();
        } catch (CartEmptyException | EmptyLinkedListException e) {
            LOGGER.error(e);
        }

        try {
            LOGGER.info("Order created and sent");
            shop.createOrder(customer); // Create an order and save it in an array of orders proper of the Shop
        } catch (CartEmptyException | CustomerNotFoundException | ElementNotFoundException |
                 EmptyLinkedListException e) {
            LOGGER.error(e);
        }



        /*Printer.printDivider();
        Printer.printDivider();

        shop.signUpCostumer(costumer2);

        costumer2.selectProduct(shop.getProduct(costumer.getUsername(), 1));
        costumer2.selectProduct(shop.getProduct(costumer.getUsername(), 2)); // two products selected from the store
        // costumer2.deleteProduct(shop.getProduct(costumer.getUsername(), 2)); // one product eliminated from the store
        System.out.println("Product Selected: ");

        Printer.listProducts(costumer2.getProductsInCart());

        costumer2.selectDelivery(delivery2);// Delivery Company Selected
        System.out.println(costumer2.getDelivery());

        costumer2.selectPayment(payment2);
        System.out.println(costumer2.getCart().getPayment());

        costumer2.selectCurrency(currency);
        System.out.println("Currency selected: " + costumer2.getCurrency());

        Printer.printDivider();
        System.out.println("Final Values");

        int total2 = shop.checkPurchase(costumer2); // Checks the values and return a total cost

        Printer.printDivider();

        System.out.println("Order created and sent");
        shop.createOrder(costumer2); // Create an order and save it in an array of orders proper of the Shop*/
    }
}


//
