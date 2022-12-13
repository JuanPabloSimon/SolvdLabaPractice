package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.*;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;

public interface IShop {

    public void addStoreProduct(Product product);

    public void incrementStock(Product product, int stock) throws ProductNotFoundException;

    public void createOrder(Customer customer) throws CustomerNotFoundException, CartEmptyException, ElementNotFoundException, EmptyLinkedListException;
}
