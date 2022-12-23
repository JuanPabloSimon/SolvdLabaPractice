package com.solvd.onlineshop.products;

import java.util.Objects;

public class Product {
    private String name;
    private int price;
    private String category;
    private String description;
    private int productID;
    private int stock = 0;

    // At some point you could try adding some stock functionality. (Optional)

    // constructor section
    public Product(String name, int price, String description, String category, int productID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.productID = productID;
    }

    // setters and getters section
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public Integer getProductID() {
        return this.productID;
    }

    public String getCategory() {
        return this.category;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    // end of section


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", productID=" + productID +
                ", stock=" + stock +
                '}';
    }
}
