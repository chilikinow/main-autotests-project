package com.company.project;

import java.util.Random;

public enum Hobby {

    Sports ("Спорт"),
    Reading ("Чтение"),
    Music ("Музыка");

    private String title;
    private static final Random RANDOM_VALUE = new Random();

    Hobby(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public static Hobby randomHobby()  {
        Hobby[] directions = values();
        return directions[RANDOM_VALUE.nextInt(directions.length)];
    }

}
