package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.lambdas.Discountable;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Cards;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.services.Payment;
import com.solvd.onlineshop.utils.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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


    @Override
    public List<Customer> filterAccountsLogged() {
        return customers.stream()
                .filter(c -> c.getisInStore().equals("true"))
                .collect(Collectors.toCollection(ArrayList<Customer>::new));

    }

    public boolean isLogged(Predicate<Customer> predicate, String username) {
        boolean test = predicate.test(getCustomer(username));
        return test;
    }

    // Store
    @Override
    public void modifyProduct(Product product, BiConsumer<Product, ArrayList<Product>> supplier) {
        supplier.accept(product, shopProducts);
    }
    // Handle Product exception
    @Override
    public void incrementStock(Product prod, Integer stock, BiConsumer<Product, Integer> consumer) {
        consumer.accept(prod, stock);
    }

    public void incrementStock(Integer stock, BiConsumer<ArrayList<Product>, Integer> consumer) {
        consumer.accept(shopProducts, stock);
    }

    @Override
    public List<Product> filterProdByPrice(Double maxValue, Double minValue) {
        return shopProducts.stream()
                .filter(p -> p.getPrice() >= minValue && p.getPrice() <= maxValue)
                .collect(Collectors.toCollection(ArrayList<Product>::new));
    }

    @Override
    public void orderProduct(Customer customer, String productName, Predicate<Customer> p) throws OutOfStockException, ProductNotFoundException {
        Product prod = getProduct(customer , productName, p);
        if (prod.getStock() > 0) {
            customer.getCart().addProduct(prod);
            prod.setStock(prod.getStock() - 1);
        } else {
            String msg = String.format("Out of stock for product %s", prod.getName());
            throw new OutOfStockException(msg);
        }
    }

    @Override
    public void deleteProduct(Customer customer, String productName, Predicate<Customer> p) throws ElementNotFoundException, EmptyLinkedListException, ProductNotFoundException {
        Product prod = getProduct(customer, productName, p);
        customer.getCart().removeProduct(prod.getProductID());
        prod.setStock(prod.getStock() + 1);
    }

    public void selectPayment(Customer customer, Payment payment) {
        customer.getCart().selectPayment(payment);
    }

    public void selectCard(Customer customer, String card) {
        customer.getCart().selectCard(card);
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
    public void createOrder(Customer customer, Predicate<Customer> p, Discountable<Double, Cards> d) throws CartEmptyException, ElementNotFoundException, EmptyLinkedListException {
        if (this.isLogged(p, customer.getUsername())) {
            if (customer.getProductsInCart().getSize() > 0) {
                Order order = new Order(customer, customer.getDelivery(), customer.getCart().getCard(), getTotalPrice(customer, d), customer.getProductsInCart().getAll(), customer.getCurrency());
                LOGGER.info(order);
            } else {
                throw new CartEmptyException("There are no products in cart.");
            }
        } else {
            throw new CustomerNotFoundException("You need to be logged for this");
        }
    }

    // end of section

    // getters and setters
    public String getName() {
        return this.name;
    }
    public Product getProduct(Customer customer, String productName, Predicate<Customer> p) throws ProductNotFoundException {
        if (this.isLogged(p, customer.getUsername())) {
            for (Product product : shopProducts) {
                if (product.getName() == productName) {
                    return product;
                }
            }
            throw new ProductNotFoundException("Product: " + productName + " not found");
        }
        throw new CustomerNotFoundException("Please LogIn");
    }
    public int getTotalPrice(Customer customer, Discountable<Double, Cards> d) throws CartEmptyException, EmptyLinkedListException {
        if (customer.getProductsInCart().getSize() > 0) {  // Check if there are products
            Integer total = Calculator.total(customer, d);
            return total;
        } else {
            throw new CartEmptyException("There are no Products added in the cart");
        }
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
    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }
    public ArrayList<Product> getCustomerProducts(Customer customer) throws EmptyLinkedListException {
        return customer.getProductsInCart().getAll();
    }
    // end of section
}
