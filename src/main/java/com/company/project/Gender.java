package com.company.project;

import java.util.Random;

public enum Gender {

    Male ("Мужчина"),
    Female ("Женщина"),
    Other ("Иное");

    private String title;
    private static final Random RANDOM_VALUE = new Random();

    Gender(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public static Gender randomGender()  {
        Gender[] directions = values();
        return directions[RANDOM_VALUE.nextInt(directions.length)];
    }

}
