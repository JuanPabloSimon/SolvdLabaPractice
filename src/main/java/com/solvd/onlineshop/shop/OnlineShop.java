package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.services.PaymentMethod;
import com.solvd.onlineshop.utils.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class OnlineShop implements IShop, IAccounts, IShopping {
    private static Logger LOGGER = LogManager.getLogger(OnlineShop.class);
    private String name;
    private String storeType;
    private ArrayList<Product> shopProducts;
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;

    // constructor section

    public OnlineShop(String name, String storeType) {
        this.name = name;
        this.storeType = storeType;
        this.shopProducts = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // end of section

    // methods section

    // Accounts
    @Override
    public void signUpCostumer(Customer customer) {
        customers.add(customer);
        logInCostumer(customer.getUsername());
    }

    @Override
    public void signUpCostumer(String username, String firstname, String lastname, int age, String email, String address) {
        Customer customer = new Customer(username, firstname, lastname, age, email, address);
        customers.add(customer);
        logInCostumer(customer.getUsername());
    }

    @Override
    public void logInCostumer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                customer.setInStore(true);
        }
    }

    @Override
    public void logOut(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                customer.setInStore(false);
        }
    }

    public boolean isLogged(Customer customer) throws CustomerNotFoundException {
        for (Customer custom : customers) {
            if (custom.getUsername().equals(customer.getUsername())) {
                return true;
            }
        }
        return false;
    }

    // Store

    @Override
    public void addStoreProduct(Product product) {
        shopProducts.add(product);
    }

    @Override
    public void incrementStock(Product product, int stock) throws ProductNotFoundException {
        for (Product prod : shopProducts) {
            if (prod.getProductID() == product.getProductID()) {
                prod.setStock(stock);
                return;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    public void incrementStock(int stock) {
        for (Product product : shopProducts) {
            product.setStock(stock);
        }
    }

    @Override
    public void orderProduct(Customer customer, String productName) throws OutOfStockException, ProductNotFoundException {
        Product prod = getProduct(customer , productName);
        if (prod.getStock() > 0) {
            customer.getCart().addProduct(prod);
            prod.setStock(prod.getStock() - 1);
        } else {
            String msg = String.format("Out of stock for product %s", prod.getName());
            throw new OutOfStockException(msg);
        }
    }

    @Override
    public void deleteProduct(Customer customer, String productName) throws ElementNotFoundException, EmptyLinkedListException, ProductNotFoundException {
        Product prod = getProduct(customer, productName);
        customer.getCart().removeProduct(prod.getProductID());
        prod.setStock(prod.getStock() + 1);
    }

    public void selectPayment(Customer customer, PaymentMethod payment) {
        customer.getCart().selectPayment(payment);
    }

    public void selectDelivery(Customer customer, DeliveryCompany delivery) {
        if (delivery.isAvailable()) {
            customer.setDelivery(delivery);
        } else {
            throw new DeliveryNotAvailableException("Delivery not available, please select between possible options");
        }
    }

    public void selectCurrency(Customer customer, Currency currency) {
        if (currency.isAvailable()) {
            customer.setCurrency(currency);
        } else {
            throw new CurrencyNotAvailableException("Currency not available, please check de possible options");
        }
    }

    @Override
    public void createOrder(Customer customer) throws CartEmptyException, ElementNotFoundException, EmptyLinkedListException {
        if (this.isLogged(customer)) {
            if (customer.getProductsInCart().getSize() > 0) {
                Order order = new Order(customer, customer.getDelivery(), customer.getCart().getPayment(), checkPurchase(customer), customer.getProductsInCart().getAll(), customer.getCurrency());
                LOGGER.info(order);
            } else {
                throw new CartEmptyException("There are no products in cart.");
            }
        } else {
            throw new CustomerNotFoundException("You need to be logged for this");
        }
    }

    public int checkPurchase(Customer customer) throws CartEmptyException, EmptyLinkedListException {
        if (customer.getProductsInCart().getSize() > 0) {  // Check if there are products
            int total = Calculator.total(customer);
            return total;
        } else {
            throw new CartEmptyException("There are no Products added in the cart");
        }
    }
    // end of section

    // getters and setters
    public String getName() {
        return this.name;
    }


    public Product getProduct(Customer customer, String productName) throws ProductNotFoundException {
        if (this.isLogged(customer)) {
            for (Product product : shopProducts) {
                if (product.getName() == productName) {
                    return product;
                }
            }
            throw new ProductNotFoundException("Product: " + productName + " not found");
        }
        throw new CustomerNotFoundException("Please LogIn");
    }


    public Customer getCustomer(String username) throws CustomerNotFoundException {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                return customer;
        }
        throw new CustomerNotFoundException("You are not Logged, please log In");
    }

    public ArrayList<Product> getShopProducts() {
        return shopProducts;
    }

    public ArrayList<Product> getCustomerProducts(Customer customer) throws EmptyLinkedListException {
        return customer.getProductsInCart().getAll();
    }
    // end of section
}
