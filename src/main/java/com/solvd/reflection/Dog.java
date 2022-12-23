package com.solvd.reflection;

public class Dog {
    private String name;
    private Integer age;
    private String color;

    public Dog(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public Integer getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void bark() {
        System.out.println("Guau!");
    }

    private void eatPlants() {
        System.out.println("I shouldn't be doing this");
    }
}
