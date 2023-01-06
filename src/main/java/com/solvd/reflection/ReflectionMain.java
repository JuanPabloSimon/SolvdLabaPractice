package com.solvd.reflection;

import com.solvd.onlineshop.people.Customer;
import com.solvd.onlineshop.products.Product;
import com.solvd.onlineshop.shop.OnlineShop;
import com.solvd.onlineshop.utils.Printer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.BiConsumer;


public class ReflectionMain {
    private static final Logger LOGGER = LogManager.getLogger(ReflectionMain.class);

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> shopClass = Class.forName("com.solvd.onlineshop.shop.OnlineShop");
        Object shop = shopClass.getConstructor(String.class, String.class).newInstance("Apple", "Technology Shop");
        Class<?> clientClass = Class.forName("com.solvd.onlineshop.people.Customer");
        Object client = clientClass.getConstructor(String.class, String.class, String.class, Integer.class, String.class, String.class).newInstance("JuanS", "Juan", "Simon", 21, "jsimon@mail.com", "Street 16 345");


        // Print information about class
        LOGGER.info(String.format(
                "Parent class of %s is %s", shopClass.getSimpleName(), shopClass.getSuperclass().getSimpleName()
        ));

        // Print interfaces
        Class<?>[] interfaces = {};
        Class<?> currentClass = shopClass;
        while (currentClass != null) {
            interfaces = ArrayUtils.addAll(interfaces, currentClass.getInterfaces());
            currentClass = currentClass.getSuperclass();
        }
        LOGGER.info(String.format(
                "%s implements %s\n",
                shopClass.getSimpleName(),
                Arrays.toString(Arrays.stream(interfaces).map(Class::getSimpleName).toArray())
        ));

        // Print fields
        printFields(shopClass);
        printFields(shopClass.getSuperclass());

        // Print methods
        printMethods(shopClass);
        printMethods(shopClass.getSuperclass());

        // Call methods with parameters
        LOGGER.info("Calling methods using reflection.");
        shopClass.getMethod("signUpCostumer", String.class, String.class, String.class, Integer.class, String.class, String.class).invoke(shop, "JuanS", "Juan", "Simon", 21, "jsimon@mail.com", "Street 16 345" );
        System.out.println(shopClass.getMethod("getCustomers").invoke(shop));
    }

    public static void printFields(Class<?> objectClass) {
        LOGGER.info(String.format("Declared fields of %s: ", objectClass.getSimpleName()));
        for (Field field : objectClass.getDeclaredFields()) {
            LOGGER.info(field.getName() + ":");
            LOGGER.info("    modifiers: " + Modifier.toString(field.getModifiers()));
            LOGGER.info("    type: " + field.getType());
        }
        LOGGER.info("\n");
    }

    public static void printMethods(Class<?> objectClass) {
        LOGGER.info(String.format("Declared methods of %s: ", objectClass.getSimpleName()));
        for (Method method : objectClass.getDeclaredMethods()) {
            LOGGER.info(method.getName() + ":");
            LOGGER.info("    modifiers: " + Modifier.toString(method.getModifiers()));
            LOGGER.info("    parameter types: " + Arrays.toString(method.getParameterTypes()));
        }
        LOGGER.info("\n");
    }
}
