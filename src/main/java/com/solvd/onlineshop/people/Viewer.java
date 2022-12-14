package com.solvd.onlineshop.people;

import com.solvd.onlineshop.products.Product;

public class Viewer extends Person {

    private final boolean isInStore = true;

    public Viewer(String firstName, String lastName, int age, String email, String address) {
        super(firstName, lastName, age, email, address);
    }


}
