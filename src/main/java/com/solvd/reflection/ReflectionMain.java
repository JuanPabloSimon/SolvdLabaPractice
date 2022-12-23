package com.solvd.reflection;

import com.solvd.onlineshop.utils.Printer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMain {
    public static void main(String[] args) throws Exception {
        Dog myDog = new Dog("Coral", 1, "Light-brown");

        // FIELDS

        Field[] dogFields = myDog.getClass().getDeclaredFields();
        for (Field field : dogFields) {
            System.out.println(field.getName());
        }

        Printer.printDivider();

        // Trying to change the value of a private field without reflection (it would fail)
        /*for (Field field : dogFields) {
            if (field.getName().equals("name")) {
                field.name = "Toto";
            }
        }*/

        // Changing the value of a private field with reflection
        for (Field field : dogFields) {
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(myDog, "Toto");
            }
        }
        System.out.println(myDog.getName());
        Printer.printDivider();

        // METHODS

        Method[] dogMethods = myDog.getClass().getDeclaredMethods();
        for (Method method : dogMethods) {
            System.out.println(method.getName() + "-  Return type: "+  method.getReturnType());
        }

        Printer.printDivider();

        // Calling a public method with reflection, this is not something really useful.
        // If the method is public you should call it directly from its class
        for (Method method : dogMethods) {
            if (method.getName().equals("bark")) {
                method.invoke(myDog);
            }
        }

        Printer.printDivider();

        // Calling a private method with reflection
        for (Method method : dogMethods) {
            if (method.getName().equals("eatPlants")) {
                method.setAccessible(true); // making it accessible
                method.invoke(myDog);
            }
        }


    }
}
