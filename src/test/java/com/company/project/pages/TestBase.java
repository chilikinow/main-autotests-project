package com.company.project.pages;

import com.company.project.Student;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TestBase {

    public Student student;
    public RegistrationPage registrationPage;
    public Faker faker;
    public static final SimpleDateFormat sdfBirthdayFaker;

    static {
        sdfBirthdayFaker = new SimpleDateFormat("dd.MM.yyyy");
    }

    {
        student = new Student();
        registrationPage = new RegistrationPage();
        faker = new Faker(new Locale("en-US"));
    }
}
