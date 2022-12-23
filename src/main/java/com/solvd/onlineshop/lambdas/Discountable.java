package com.solvd.onlineshop.lambdas;

@FunctionalInterface
public interface Discountable<Double, Cards> {
    Double apply( Double d, Cards c);
}
