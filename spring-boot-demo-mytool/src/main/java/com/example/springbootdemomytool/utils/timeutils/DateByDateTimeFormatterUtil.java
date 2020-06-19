package com.example.springbootdemomytool.utils.timeutils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName DateByDateTimeFormatterUtil
 * @Description
 * 线程安全- SimpleDateFormat.format()
 * 使用DateTimeFormatter
 * @Author Leo
 * @Date 2020/5/22 15:26
 * @Version 1.0
 */
@Slf4j
public class DateByDateTimeFormatterUtil {

    public static LocalDate format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("", formatter);
        System.out.println(formatter.format(date));
        return date;
    }


}
