package com.chynten.scheduler;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * Created by chintankumar.patel on 6/1/2016.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        /*Date d1 = new Date();
        LocalDateTime localDateTime = LocalDateTime.now().fromDateFields(d1);

        System.out.println(d1);

        int dayOfMonth = localDateTime.getDayOfMonth();
        System.out.println(dayOfMonth);

        int monthOfYear = localDateTime.getMonthOfYear();
        System.out.println(monthOfYear);

        LocalDateTime.Property property = localDateTime.dayOfWeek();
        System.out.println(property.getAsShortText());

        int hourOfDay = localDateTime.getHourOfDay();
        System.out.println(hourOfDay);

        int minuteOfHour = localDateTime.getMinuteOfHour();
        System.out.println(minuteOfHour);*/
        SpringApplication.run(Main.class, args);
    }
}
