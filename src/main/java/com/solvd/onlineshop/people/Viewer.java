package com.solvd.onlineshop.people;

import com.solvd.onlineshop.products.Product;

public class Viewer extends Person {

    private final boolean isInStore = true;

    @Override
    public void selectProduct(Product product) {
    }

    @Override
    public void deleteProduct(Product product) {
    }

    public Viewer(String firstName, String lastName, int age, String email, String address) {
        super(firstName, lastName, age, email, address);
    }


}
