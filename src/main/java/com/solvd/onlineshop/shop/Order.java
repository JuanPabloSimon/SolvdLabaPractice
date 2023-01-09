package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.lambdas.Discountable;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.services.Cards;
import com.solvd.onlineshop.services.Currency;
import com.solvd.onlineshop.services.DeliveryCompany;
import com.solvd.onlineshop.utils.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.UUID;

public class Order {
    private static Logger LOGGER = LogManager.getLogger(Order.class);
    public OrderState state;
    private String orderId;
    private DeliveryCompany deliveryCompany;
    private Cards cardSelected;
    private int totalValue;
    private ArrayList<Product> products;
    private Customer customer;
    private Currency currency;

    // constructor section

    public Order(Customer customer, DeliveryCompany deliveryCompany, Cards card, int totalValue, ArrayList<Product> products, Currency currency) {
        this.deliveryCompany = deliveryCompany;
        this.customer = customer;
        this.cardSelected = card;
        this.totalValue = totalValue;
        this.products = products;
        this.currency = currency;
    }

    public Order(Customer customer) {
        this.state = OrderState.PENDING;
        this.customer = customer;
        this.orderId = UUID.randomUUID().toString();
    }

    // end of section

    // methods section
    public int calculatesTotal(Discountable<Double, Cards> d) throws EmptyLinkedListException {
        Integer total = Calculator.total(customer, d);
        return total;
    }

    public void send(Discountable<Double, Cards> d) throws EmptyLinkedListException {
        if (deliveryCompany.isAvailable() && currency.isAvailable() && !cardSelected.equals(null)) {
            this.state = OrderState.PROCESSING;
            LOGGER.info(state.getValue());
            this.totalValue = calculatesTotal(d);
            if (totalValue > 0) {
                this.state = OrderState.COMPLETED;
                LOGGER.info(state.getValue());
            }
        } else {
            this.state = OrderState.FAILED;
        }
    }

    // end of section

    // getters and setters

    public void setDeliveryCompany(DeliveryCompany deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public void setCardSelected(Cards cardSelected) {
        this.cardSelected = cardSelected;
    }


    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getTotal() {
        return this.totalValue;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public OrderState getState() {
        return state;
    }

    public DeliveryCompany getDeliveryCompany() {
        return deliveryCompany;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getCurrencySymbol() {
        return currency.getCurrencyType().getCurrencySymbol();
    }

    // end of section


    @Override
    public String toString() {
        return "Order{" +
                "state= " + state +
                ", \norderId= '" + orderId + '\'' +
                ", \ndeliveryCompany= " + deliveryCompany +
                ", \ncardSelected= " + cardSelected +
                ", \ntotalValue= " + this.getCurrencySymbol() + totalValue +
                ", \nproducts=" + products +
                ", \ncustomer= {" + customer.getUsername() + ", address= '" + customer.getAddress() +
                "}, \ncurrency=" + currency +
                '}';
    }
}
