package com.example.springbootdemomytool.utils.jodademo;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.Locale;

/**
 * @ClassName JodaDemo
 * @Description
 * @Author hup
 * @Date 2020/8/25 13:46
 * @Version 1.0
 */
public class JodaDemo {
    public static void main(String[] args) {
        // 当前时间
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
        // 输出英文星期
        String asText = dateTime.dayOfWeek().getAsText(Locale.ENGLISH);
        System.out.println(asText);
        // 本地日期格式
        LocalDate localDate = dateTime.toLocalDate();
        System.out.println(localDate);
        //日期格式化
        String dateFormat = dateTime.toString(DateTimeFormat.forPattern("yyyy年M月d日H时m分s秒"));
        System.out.println(dateFormat);

        // 日期计算
        // 加100小时是星期几
        DateTime.Property plus100Hours = dateTime.plusHours(100).dayOfWeek();
        System.out.println(plus100Hours);
        // 100天后的日期
        LocalDate localDate1 = dateTime.plusDays(100).toLocalDate();
        System.out.println(localDate1);
        // 10年前的今天是星期几
        String asText1 = dateTime.minusYears(10).dayOfWeek().getAsText();
        System.out.println(asText1);
        // 离2020-12-31还有多少小时
        int hours = Hours.hoursBetween(dateTime, new DateTime("2020-12-31")).getHours();
        System.out.println(hours);

        // 当前可变时间
        MutableDateTime mutableDateTime = new MutableDateTime();
        // 10年后的日期
        DateTime plusYears = dateTime.plusYears(10);
        while (mutableDateTime.isBefore(plusYears)) {
            // 循环一次加一天
            mutableDateTime.addDays(1);
            // 是13号，并且是星期五
            if (mutableDateTime.getDayOfMonth() == 13 && mutableDateTime.getDayOfWeek() == 5) {
                // 打印出10年所有的黑色星期五
                System.out.println("黑色星期五： " + mutableDateTime);
            }
        }

        // 时区时间
        // 此时伦敦市的时间
        DateTime dateTime1 = dateTime.withZone(DateTimeZone.forID("Europe/London"));
        System.out.println(dateTime1);
        // 计算出标准时间
        DateTime dateTime2 = dateTime.withZone(DateTimeZone.UTC);
        System.out.println(dateTime2);

        // from Joda to java.util.Date
        // Joda DateTime to JDK Date
        Date jdkDate = dateTime.toDate();
        dateTime = new DateTime(jdkDate);

    }



}
