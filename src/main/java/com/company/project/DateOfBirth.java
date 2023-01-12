package com.company.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOfBirth {

    private LocalDate date;

    public DateOfBirth(String date){
        String europeanDatePattern = "dd.MM.yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        this.date = LocalDate.parse(date, europeanDateFormatter);
    }

    public DateOfBirth(LocalDate date){
        this.date = date;
    }

    public LocalDate getLocalDate(){
        return this.date;
    }

    public String getMonthStartToUppercase(){
        String month = this.date.getMonth().name();
        return month.substring(0, 1) + month.substring(1).toLowerCase();
    }

    public String getDayByTwoCharString(){
        String day = String.valueOf(this.date.getDayOfMonth());
        if (day.length() < 2){
            return "0" + day;
        } else {
          return day;
        }
    }
}
