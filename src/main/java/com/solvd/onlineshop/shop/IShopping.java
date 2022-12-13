package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.exceptions.OutOfStockException;
import com.solvd.onlineshop.products.Product;

public interface IShopping {

    public void selectProduct(Product product) throws OutOfStockException;

    public void deleteProduct(Product product) throws EmptyLinkedListException, ElementNotFoundException;


}
