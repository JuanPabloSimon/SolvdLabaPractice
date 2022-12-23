package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.exceptions.CartEmptyException;
import com.solvd.onlineshop.exceptions.CustomerNotFoundException;
import com.solvd.onlineshop.exceptions.ElementNotFoundException;
import com.solvd.onlineshop.exceptions.EmptyLinkedListException;
import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public interface IShop {

    //This method allows you to add and also remove it
    public void modifyProduct(Product product, BiConsumer<Product, ArrayList<Product>> modifier);

    public List<Product> filterProdByPrice(Double maxValue, Double Double);


    public void incrementStock(Product product, Integer stock, BiConsumer<Product, Integer> consumer);

    public void createOrder(Customer customer) throws CustomerNotFoundException, CartEmptyException, ElementNotFoundException, EmptyLinkedListException;
}
