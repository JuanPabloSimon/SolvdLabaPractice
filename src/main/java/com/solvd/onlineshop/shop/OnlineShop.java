package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.utils.Calculator;
import org.apache.logging.log4j.*;

import java.util.ArrayList;
import java.util.Queue;

public class OnlineShop implements IShop, IAccounts {
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

    // hacer algo con esto public void logInCostumer(Viewer viewer) {
    //   System.out.println("You need to have an account to logIn " + "\n Please first SignUp");
    //}

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

    public boolean isLogged(String username) throws CustomerNotFoundException {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
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
    public void createOrder(Customer customer) throws CartEmptyException, ElementNotFoundException, EmptyLinkedListException {
        if (this.isLogged(customer.getUsername())) {
            if (customer.getProductsInCart().getSize() > 0) {
                Order order = new Order(customer, customer.getDelivery(), customer.getCart().getPayment(), checkPurchase(customer), customer.getProductsInCart().getAll(), customer.getCurrency());
                LOGGER.info(order.getOrder());
            } else {
                throw new CartEmptyException("There are no products in cart.");
            }
        } else {
            throw new CustomerNotFoundException("You need to be logged for this");
        }
    }

    public int checkPurchase(Customer customer) throws CartEmptyException, EmptyLinkedListException {
        if (customer.getProductsInCart().getSize() > 0) {  // Check if there are products
            int subTotal = 0;
            subTotal += Calculator.totalProducts(customer.getProductsInCart());
            LOGGER.info("Total Products Value: " + customer.getCurrency().getSymbol() + customer.getCurrency().changeOfCurrency(subTotal, customer.getCurrency().getName()));

            int total = Calculator.total(subTotal, customer.getDelivery().getPrice(), customer.getCart().getPayment(), customer.getCurrency());
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


    public Product getProduct(String username, int productID) throws ProductNotFoundException {
        if (this.isLogged(username)) {
            for (Product product : shopProducts) {
                if (product.getProductID() == productID) {
                    return product;
                }
            }
            throw new ProductNotFoundException("Product with ID: " + productID + " not found");
        }
        throw new CustomerNotFoundException("Please LogIn");

    }


    public Customer getCostumer(String username) throws CustomerNotFoundException {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                return customer;
        }
        throw new CustomerNotFoundException("You are not Logged, please log In");
    }

    public ArrayList<Product> getShopProducts() {
        return shopProducts;
    }
    // end of section
}
