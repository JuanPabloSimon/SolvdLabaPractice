package com.solvd.onlineshop.lambdas;
@FunctionalInterface
public interface Printable<T> {
    void print(T t);
}
