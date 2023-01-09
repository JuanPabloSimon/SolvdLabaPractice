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

        // Products added to the store and then increment their stock
        shop.manageProducts(product1, (prod, shopProds) -> {
            shopProds.add(prod);
        });
        shop.manageProducts(product2, (prod, shopProds) -> {
            shopProds.add(prod);
        });
        shop.manageProducts(product3, (prod, shopProds) -> {
            shopProds.add(prod);
        });

        shop.incrementStock(10, (prod, stock) -> {
            for (Product p : prod) {
                p.setStock(stock);
            }
        });

        shop.incrementStock(product1, 30, (prod, stock) -> {
            prod.setStock(30);
        });
        LOGGER.info(shop.getName());
        LOGGER.info("Products of the store: ");

        Printer.listProducts(shop.getShopProducts(),
                (prods) -> {
                    for (Object prod : prods) {
                        LOGGER.info(prod);
                    }
                });

        LOGGER.info("Products of the store Filtered: ");
        Printer.listProducts(shop.filterProdByPrice(1100.00, 900.00),
                (prods) -> {
                    for (Object prod : prods) {
                        LOGGER.info(prod);
                    }
                });

        Printer.printDivider();

        // Services initialization

        DeliveryCompany delivery = new DeliveryCompany("Andreani", 10, 2);
        DeliveryCompany delivery2 = new DeliveryCompany("Correo Argentino", 8, 5);
        Payment payment = new Payment("Debit");
        Payment payment2 = new Payment("Credit");
        Currency currency = new Currency(CurrencyType.EURO);

        // Costumer instantiation

        Customer customer = new Customer("John_123", "John", "Smith", 22, "john@mail.com", "Street 1 345");
        Customer customer2 = new Customer("JuanS", "Juan", "Simon", 21, "jsimon@mail.com", "Street 16 345");

        // Start of the Purchase

        // costumer logged in the page
        shop.signUpCostumer(customer);

        // Addition and deletion of some products
        try {
            shop.orderProduct(customer, "Iphone 14", (c) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(c.getUsername())) {
                        return true;
                    }
                }
                return false;
            });
            shop.orderProduct(customer, "Iphone 13", (c) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(c.getUsername())) {
                        return true;
                    }
                }
                return false;
            });
            shop.deleteProduct(customer, "Iphone 14", (c) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(c.getUsername())) {
                        return true;
                    }
                }
                return false;
            });
            LOGGER.info("Product Selected: ");
            Printer.listProducts(shop.getCustomerProducts(customer),
                    (prods) -> {
                        for (Object prod : prods) {
                            LOGGER.info(prod);
                        }
                    });
        } catch (ProductNotFoundException | OutOfStockException | EmptyLinkedListException |
                 ElementNotFoundException e) {
            LOGGER.error(e);
        }

        // Selection of services, payment method and card
        try {
            shop.selectDelivery(customer, delivery);// Delivery Company Selected
            LOGGER.info(customer.getOrder().getDeliveryCompany());
            shop.selectPayment(customer, payment); // Payment Method Selected
            shop.selectCard(customer, "mastercard");
            LOGGER.info(customer.getCart().getCard());
            shop.selectCurrency(customer, currency); // Currency selected
            LOGGER.info("Currency selected: " + customer.getOrder().getCurrency());
            Printer.printDivider();
        } catch (EmptyLinkedListException e) {
            LOGGER.error(e);
        }


        // Get total amount and details of the purchase
        try {
            int total = shop.getTotalPrice(customer, (amount, card) -> amount - (amount / card.getDiscount())); // Checks the values and return a total cost
            LOGGER.info("Final Values");
            Printer.printValues(customer,
                    (amount, card) -> amount - (amount / card.getDiscount()));
            Printer.printDivider();
        } catch (CartEmptyException | EmptyLinkedListException e) {
            LOGGER.error(e);
        }

        // finish and sending of order
        try {
            shop.finishOrder(customer, (cust) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(cust.getUsername())) {
                        return true;
                    }
                }
                return false;
            }, (amount, card) -> amount - (amount / card.getDiscount()));
            LOGGER.info(customer.getOrder().toString());
        } catch (CartEmptyException | CustomerNotFoundException |
                 EmptyLinkedListException e) {
            LOGGER.error(e);
        }

        Printer.printDivider();
        Printer.printDivider();
        Printer.printDivider();
        Printer.printDivider();


        // Costumer2 won't be able to continue because it's not logged (will throw an exception)
        LOGGER.info("Product Selected: ");
        try {
            shop.orderProduct(customer2, "Iphone 14", (c) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(c.getUsername())) {
                        return true;
                    }
                }
                return false;
            });
            shop.orderProduct(customer2, "Iphone 13", (c) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(c.getUsername())) {
                        return true;
                    }
                }
                return false;
            });// two products selected from the store
            shop.deleteProduct(customer2, "Iphone 14", (c) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(c.getUsername())) {
                        return true;
                    }
                }
                return false;
            });
            Printer.listProducts(shop.getCustomerProducts(customer),
                    (prods) -> {
                        for (Object prod : prods) {
                            LOGGER.info(prod);
                        }
                    });
        } catch (ProductNotFoundException | OutOfStockException | EmptyLinkedListException |
                 ElementNotFoundException e) {
            LOGGER.error(e);
        }

        try {
            shop.selectDelivery(customer2, delivery);// Delivery Company Selected
            LOGGER.info(customer2.getOrder().getDeliveryCompany());
            shop.selectPayment(customer2, payment); // Payment Method Selected
            shop.selectCard(customer2, "mastercard");
            LOGGER.info(customer2.getCart().getCard());
            shop.selectCurrency(customer2, currency); // Currency selected
            LOGGER.info("Currency selected: " + customer2.getOrder().getCurrency());
        } catch (EmptyLinkedListException e) {
            LOGGER.error(e);
        }
        // selection of services


        Printer.printDivider();
        LOGGER.info("Final Values");


        try {
            int total = shop.getTotalPrice(customer2, (amount, card) -> amount - (amount / card.getDiscount())); // Checks the values and return a total cost
            Printer.printValues(customer2,
                    (amount, card) -> amount - (amount / card.getDiscount()));
            Printer.printDivider();
        } catch (CartEmptyException | EmptyLinkedListException e) {
            LOGGER.error(e);
        }

        try {
            // Create an order and save it in an array of orders proper of the Shop
            shop.finishOrder(customer2, (cust) -> {
                for (Customer custom : shop.getCustomers()) {
                    if (custom.getUsername().equals(cust.getUsername())) {
                        return true;
                    }
                }
                return false;
            }, (amount, card) -> amount - (amount / card.getDiscount()));
            LOGGER.info(customer2.getOrder().toString());
        } catch (CartEmptyException | CustomerNotFoundException |
                 EmptyLinkedListException e) {
            LOGGER.error(e);
        }

    }
}

//
