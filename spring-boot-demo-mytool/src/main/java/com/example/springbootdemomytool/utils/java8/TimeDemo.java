package com.example.springbootdemomytool.utils.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @ClassName TimeDemo
 * @Description
 *  url: https://mp.weixin.qq.com/s/USyFqFKKssmzSN1xd3jPhg
 * 总结：
 * Java 8日期时间API的重点
 * 1）提供了javax.time.ZoneId 获取时区。
 * 2）提供了LocalDate和LocalTime类。
 * 3）Java 8 的所有日期和时间API都是不可变类并且线程安全，而现有的Date和Calendar API中的java.util.Date和SimpleDateFormat是非线程安全的。
 * 4）主包是 java.time,包含了表示日期、时间、时间间隔的一些类。里面有两个子包java.time.format用于格式化， java.time.temporal用于更底层的操作。
 * 5）时区代表了地球上某个区域内普遍使用的标准时间
 * @Author H
 * @Date 2021/1/11 15:36
 * @Version 1.0
 */
public class TimeDemo {

    public static void main(String[] args) {
        now();
        ymd();
        dateCompare();
        time();
        time1();
        time2();
        time3();
        time31();
        time4();
        dateCompare1();
        dateZone();
        yearMonth();
        isLeapYear();
        twoDateSub();
        nowStamp();
        dateFormat();
        dateFormat1();
    }

    // 示例1:Java 8中获取今天的日期
    public static void now() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期：" + today);
    }

    // 示例2:Java 8中获取年、月、日信息
    public static void ymd() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.println("year: " + year);
        System.out.println("month: " + month);
        System.out.println("day: " + day);
    }

    // 示例3:Java 8中处理特定日期
    public static void diyDate() {
        LocalDate date = LocalDate.of(2021, 1, 11);
        System.out.println("自定义日期：" + date);
    }

    // 示例4:Java 8中判断两个日期是否相等
    public static void dateCompare() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2021, 1, 11);

        if (date1.equals(date2)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }
    }

    // 示例5:Java 8中检查像生日这种周期性事件
    public static void checkBirthday() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2021, 1, 11);

        MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date1);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("happy birthday!");
        } else {
            System.out.println("today is not your birthday");
        }
    }

    // 示例6:Java 8中获取当前时间
    public static void time() {
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间，不含有日期： " + time);
    }

    // 示例7:Java 8中 现有时间上增加小时
    public static void time1() {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(3);
        System.out.println("after 3 hours time is: " + newTime);
    }

    // 示例8:Java 8如何计算一周后的日期
    public static void time2() {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期为： " + today);
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为： " + nextWeek);
    }

    // 示例9:Java 8计算一年前或一年后的日期
    public static void time3() {
        //LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.of(2000, 2, 29);
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期为： " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期为： " + nextYear);
    }

    // 示例9:Java 8计算一年前或一年后的日期
    public static void time31() {
        //LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.of(2000, 2, 28);
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期为： " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期为： " + nextYear);
    }

    // 示例10:Java 8的Clock时钟类
    public static void time4() {
        Clock clock = Clock.systemUTC();
        System.out.println("Clock: " + clock.millis());

        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock: " + defaultClock.millis());
    }

    // 示例11:如何用Java判断日期是早于还是晚于另一个日期
    public static void dateCompare1() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2021, 1, 12);

        if (tomorrow.isAfter(today)) {
            System.out.println("之后的日期： " + tomorrow);
        }
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("之前的日期: " + yesterday);
        }
    }

    // 示例12:Java 8中处理时区
    public static void dateZone() {
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateTime, america);
        System.out.println("Current date and time in a particular timezone: " + dateAndTimeInNewYork);
    }

    // 示例13:如何表示信用卡到期这类固定日期，答案就在YearMonth
    public static void yearMonth() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s:%d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2021, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s%n", creditCardExpiry);
    }

    // 示例14:如何在Java 8中检查闰年
    public static void isLeapYear() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("now is not a Leap year");
        }
    }

    // 示例15:计算两个日期之间的天数和月数  将来
    public static void twoDateSub() {
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, 12, 14);

        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release: "
                + periodToNextJavaRelease.getMonths()
                + ", years: " + periodToNextJavaRelease.getYears()
                + ", days: " + periodToNextJavaRelease.getDays());
    }

    // 包含时差信息的日期和时间
    public void ZoneOffset() {
        LocalDateTime dateTime = LocalDateTime.of(2018, Month.FEBRUARY, 14, 19, 20);
        ZoneOffset zoneOffset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(dateTime, zoneOffset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
    }

    // 示例16:在Java 8中获取当前的时间戳
    public static void nowStamp() {
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());
    }

    // 示例17:Java 8中如何使用预定义的格式化工具去解析或格式化日期
    public static void dateFormat() {
        String dayAfterTomorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTomorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTomorrow + "格式化后的日期为: " + formatted);
    }

    // 示例18:字符串互转日期类型
    public static void dateFormat1() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串: " + str);

        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 字符串转日期
        LocalDate date2 = LocalDate.parse(str, format2);
        System.out.println("日期类型：" + date2);
    }


}
