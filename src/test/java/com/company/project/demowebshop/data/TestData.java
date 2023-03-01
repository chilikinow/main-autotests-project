package com.company.project.demowebshop.data;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class TestData {

    public static Faker faker = new Faker(new Locale("de"));
    public static Random random = new Random();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String gender = getGender();
    public String password = faker.internet().password();

    public String getGender() {
        String[] genderArray = {"M", "F"};
        return getRandomArrayItem(genderArray);
    }

    public String getRandomArrayItem(String[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }
}
