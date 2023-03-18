package com.company.project.demowebshop.data;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.Locale;
import java.util.Random;

@Getter
public class TestData {

    public static Faker faker = new Faker(new Locale("de"));
    public static Random random = new Random();

    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String email = faker.internet().emailAddress();
    private String gender = chooseGender();
    private String password = faker.internet().password();

    private String chooseGender() {
        String[] genderArray = {"M", "F"};
        return getRandomArrayItem(genderArray);
    }

    private String getRandomArrayItem(String[] values) {
        int index = random.nextInt(values.length);
        return values[index];
    }
}
