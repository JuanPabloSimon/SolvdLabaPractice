package com.solvd.onlineshop.people;


public abstract class Person { // implement interface
    protected String name;
    protected int age;
    protected String email;
    protected String address;

    // constructor section
    public Person(String firstName, String lastName, int age, String email, String address) {
        this.name = firstName + ' ' + lastName;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    // end of section


    // setters and getters section
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
    // end of section

}

	


