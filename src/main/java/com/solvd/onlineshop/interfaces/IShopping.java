package com.solvd.onlineshop.interfaces;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.exceptions.OutOfStockException;
import com.solvd.onlineshop.exceptions.ProductNotFoundException;
import com.solvd.onlineshop.people.Customer;

import java.util.function.Predicate;

public interface IShopping {

    public void orderProduct(Customer customer, String productName, Predicate<Customer> p) throws OutOfStockException, ProductNotFoundException;

    public void deleteProduct(Customer customer, String productName, Predicate<Customer> p) throws EmptyLinkedListException, ElementNotFoundException, ProductNotFoundException;


}
