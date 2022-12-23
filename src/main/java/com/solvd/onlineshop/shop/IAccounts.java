package com.solvd.onlineshop.shop;

import com.solvd.onlineshop.people.Customer;

import java.util.List;

public interface IAccounts {

    public void signUpCostumer(Customer customer);

    public void signUpCostumer(String username, String firstname, String lastname, int age, String email, String address);

    public void logInCostumer(String username);

    public void logOut(String username);

    public List<Customer> filterAccountsLogged();
}
