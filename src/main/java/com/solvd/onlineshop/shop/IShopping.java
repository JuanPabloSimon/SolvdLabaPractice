package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.exceptions.OutOfStockException;
import com.solvd.onlineshop.exceptions.ProductNotFoundException;
import com.solvd.onlineshop.people.Customer;

public interface IShopping {

    public void orderProduct(Customer customer, String productName) throws OutOfStockException, ProductNotFoundException;

    public void deleteProduct(Customer customer, String prodcutName) throws EmptyLinkedListException, ElementNotFoundException, ProductNotFoundException;


}
