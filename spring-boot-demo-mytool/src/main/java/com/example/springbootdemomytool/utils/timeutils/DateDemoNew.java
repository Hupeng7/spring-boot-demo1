package com.example.springbootdemomytool.utils.timeutils;

import lombok.var;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @ClassName DateDemoNew
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/21 18:26
 * @Version 1.0
 */
public class DateDemoNew {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // String --> LocalDate
        LocalDate localDate = LocalDate.parse("2019-12-07");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(LocalDate.parse("2019-10-09").format(pattern));

        // String --> LocalTime
        LocalTime localTime = LocalTime.parse("07:43:53");

        // String -->LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse("2020-12-09 18:22:33", formatter);
        System.out.println(localDateTime);

        LocalDate localDate1 = LocalDate.parse("2019-12-07 07:43:53", formatter);

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDate1);

        Instant instant = Instant.now();
        System.out.println(instant);

        System.out.println("=============================================================");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        var formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm ZZZZ");
        System.out.println(formatter1.format(zonedDateTime));

        var zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
        System.out.println(zhFormatter.format(zonedDateTime));

        var usFormatter = DateTimeFormatter.ofPattern("E,MMMM/dd/yyyy HH:mm", Locale.US);
        System.out.println(usFormatter.format(zonedDateTime));

    }


}
