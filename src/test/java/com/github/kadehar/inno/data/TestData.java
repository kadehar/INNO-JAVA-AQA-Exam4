package com.github.kadehar.inno.data;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    private static final Faker FAKER = new Faker();

    public static String zipCode() {
        return FAKER.address().zipCode();
    }

    public static String lastName() {
        return FAKER.name().lastName();
    }

    public static String firstName() {
        return FAKER.name().firstName();
    }

    public static int items() {
        return ThreadLocalRandom.current().nextInt(1, 6);
    }
}
