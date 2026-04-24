package com.demoqa.utils;

import net.datafaker.Faker;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en", "US"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPhoneNumber() {
        return faker.number().digits(10);
    }

    public static String getInvalidEmail() {
        return faker.name().username() + "gmail.com";
    }
}