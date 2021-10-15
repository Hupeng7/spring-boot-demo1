package com.example.springbootdemomytool.utils.timeutils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Java8TimeUtils
 * @Description Java8日期时间工具类
 * @Author H
 * @Date 2021/9/16 10:29
 * @Version 1.0
 */
public class Java8TimeUtils {
    /**
     * 显示年月日时分秒，例如2021-09-16 11:01:22
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 仅显示年月日，例如2021-09-16
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 仅显示时分秒，例如11:01:22
     */
    public static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * 显示年月日时分秒（无符号），例如20210916110122
     */
    public static final String UNSIGNED_DATETIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * 显示年月日（无符号），例如20210916
     */
    public static final String UNSIGNED_DATE_PATTERN = "yyyyMMdd";

    /**
     * 春天
     */
    public static final Integer SPRING = 1;

    /**
     * 夏天
     */
    public static final Integer SUMMER = 2;

    /**
     * 秋天
     */
    public static final Integer AUTUMN = 3;

    /**
     * 冬天
     */
    public static final Integer WINTER = 4;

    /**
     * 星期日
     */
    public static final String SUNDAY = "星期日";

    /**
     * 星期一
     */
    public static final String MONDAY = "星期一";

    /**
     * 星期二
     */
    public static final String TUESDAY = "星期二";

    /**
     * 星期三
     */
    public static final String WEDNESDAY = "星期三";

    /**
     * 星期四
     */
    public static final String THURSDAY = "星期四";

    /**
     * 星期五
     */
    public static final String FRIDAY = "星期五";

    /**
     * 星期六
     */
    public static final String SATURDAY = "星期六";

    /**
     * 年
     */
    public static final String YEAR = "year";

    /**
     * 月
     */
    public static final String MONTH = "month";

    /**
     * 周
     */
    public static final String WEEK = "week";

    /**
     * 日
     */
    public static final String DAY = "day";

    /**
     * 时
     */
    public static final String HOUR = "hour";

    /**
     * 分
     */
    public static final String MINUTE = "minute";

    /**
     * 秒
     */
    public static final String SECOND = "second";

    /**
     * 获取当前日期和时间字符串
     *
     * @return String 日期时间字符串，例如2021-09-16 11:01:22
     */
    public static String getLocalDateTimeStr() {
        return format(LocalDateTime.now(), DATETIME_PATTERN);
    }

    /**
     * 获取当前日期字符串
     *
     * @return String 日期字符串，例如2021-09-16
     */
    public static String getLocalDateStr() {
        return format(LocalDate.now(), DATE_PATTERN);
    }

    /**
     * 获取当前时间字符串
     *
     * @return String 时间字符串，例如11:01:22
     */
    public static String getLocalTimeStr() {
        return format(LocalTime.now(), TIME_PATTERN);
    }

    /**
     * 获取当前星期字符串
     *
     * @return String 当前星期字符串，例如，星期二
     */
    public static String getDayOfWeekStr() {
        return format(LocalDate.now(), "E");
    }

    /**
     * 获取指定日期是星期几
     *
     * @param localDate 日期
     * @return String 星期几
     */
    public static String getDayOfWeekStr(LocalDate localDate) {
        String[] weekOfDays = {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};
        int dayOfWeek = localDate.getDayOfWeek().getValue() - 1;
        return weekOfDays[dayOfWeek];
    }

    /**
     * 获取日期时间字符串
     *
     * @param temporalAccessor 需要转化的日期时间
     * @param pattern          时间格式
     * @return String 日期时间字符串
     */
    public static String format(TemporalAccessor temporalAccessor, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(temporalAccessor);
    }

    /**
     * 日期时间字符串转换为日期时间
     *
     * @param localDateTimeStr 日期时间字符串
     * @param pattern          日期时间格式，例如DATETIME_PATTERN
     * @return LocalDateTime 日期时间
     */
    public static LocalDateTime parseLocalDateTime(String localDateTimeStr, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(localDateTimeStr, dateTimeFormatter);
    }

    /**
     * 日期字符串转换为日期
     *
     * @param localDateStr 日期字符串
     * @param pattern      日期格式
     * @return LocalDate 日期
     */
    public static LocalDate parseLocalDate(String localDateStr, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(localDateStr, dateTimeFormatter);
    }

    /**
     * 获取指定日期时间加上指定数量日期时间单位之后的日期时间
     *
     * @param localDateTime 日期时间
     * @param num           数量
     * @param chronoUnit    日期时间单位
     * @return LocalDateTime 新的日期时间
     */
    public static LocalDateTime plus(LocalDateTime localDateTime, int num, ChronoUnit chronoUnit) {
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 获取指定日期时间减去指定数量日期时间单位之后的日期时间
     *
     * @param localDateTime 日期时间
     * @param num           数量
     * @param chronoUnit    日期时间单位
     * @return LocalDateTime  新的日期时间
     */
    public static LocalDateTime minus(LocalDateTime localDateTime, int num, ChronoUnit chronoUnit) {
        return localDateTime.minus(num, chronoUnit);
    }

    /**
     * 根据ChronoUnit计算两个日期时间之间相隔日期时间
     *
     * @param start      开始日期时间
     * @param end        结束日期时间
     * @param chronoUnit 日期时间单位
     * @return long  相隔日期时间
     */
    public static long getChronoUnitBetween(LocalDateTime start, LocalDateTime end, ChronoUnit chronoUnit) {
        return Math.abs(start.until(end, chronoUnit));
    }

    /**
     * 根据ChronoUnit计算两个日期之间相隔年数或月数或天数
     *
     * @param start      开始日期
     * @param end        结束日期
     * @param chronoUnit 日期时间单位
     * @return long 相隔念书或月数或天数
     */
    public static long getChronoUnitBetween(LocalDate start, LocalDate end, ChronoUnit chronoUnit) {
        return Math.abs(start.until(end, chronoUnit));
    }

    /**
     * 获取本年第一天的日期字符串
     *
     * @return
     */
    public static String getFirstDayOfYearStr() {
        return getFirstDayOfYearStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期当年第一天的日期字符串
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getFirstDayOfYearStr(LocalDateTime localDateTime) {
        return getFirstDayOfYearStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期当年第一天的日期字符串，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return String
     */
    public static String getFirstDayOfYearStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.withDayOfYear(1).withHour(0).withMinute(0).withSecond(0), pattern);
    }

    /**
     * 获取本年最后一天的日期字符串
     *
     * @return
     */
    public static String getLastDayOfYearStr() {
        return getLastDayOfYearStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期当年最后一天的日期字符串
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getLastDayOfYearStr(LocalDateTime localDateTime) {
        return getLastDayOfYearStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期当年最后一天的日期字符串，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return String
     */
    public static String getLastDayOfYearStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.with(TemporalAdjusters.lastDayOfYear())
                .withHour(23).withMinute(59).withSecond(59), pattern);
    }

    /**
     * 获取本月第一天的日期字符串
     *
     * @return
     */
    public static String getFirstDayOfMonthStr() {
        return getFirstDayOfMonthStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期当月第一天的日期字符串
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getFirstDayOfMonthStr(LocalDateTime localDateTime) {
        return getFirstDayOfMonthStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期当月第一天的日期字符串，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return
     */
    public static String getFirstDayOfMonthStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), pattern);
    }

    /**
     * 获取本月最后一天的日期字符串
     *
     * @return
     */
    public static String getLastDayOfMonthStr() {
        return getLastDayOfMonthStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期当月最后一天的日期字符串
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getLastDayOfMonthStr(LocalDateTime localDateTime) {
        return getLastDayOfMonthStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期当月最后一天的日期字符串，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return
     */
    public static String getLastDayOfMonthStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23).withMinute(59).withSecond(59), pattern);
    }

    /**
     * 获取本周第一天的日期字符串
     *
     * @return
     */
    public static String getFirstDayOfWeekStr() {
        return getFirstDayOfWeekStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期当周第一天的日期字符串，这里第一天为周一
     *
     * @param localDateTime
     * @return
     */
    public static String getFirstDayOfWeekStr(LocalDateTime localDateTime) {
        return getFirstDayOfWeekStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期当周第一天的日期字符串，这里第一天为周一，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return
     */
    public static String getFirstDayOfWeekStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.with(DayOfWeek.MONDAY)
                .withHour(0).withMinute(0).withSecond(0), pattern);
    }

    /**
     * 获取本周最后一天的日期字符串
     *
     * @return
     */
    public static String getLastDayOfWeekStr() {
        return getLastDayOfWeekStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期当周最后一天的日期字符串，这里最后一天为周日
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getLastDayOfWeekStr(LocalDateTime localDateTime) {
        return getLastDayOfWeekStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期当周最后一天的日期字符串，这里最后一天为周日，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return
     */
    public static String getLastDayOfWeekStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.with(DayOfWeek.SUNDAY)
                .withHour(23).withMinute(59).withSecond(59), pattern);
    }

    /**
     * 获取今天开始时间的日期字符串
     *
     * @return
     */
    public static String getStartTimeOfDayStr() {
        return getStartTimeOfDayStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期开始时间的日期字符串
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getStartTimeOfDayStr(LocalDateTime localDateTime) {
        return getStartTimeOfDayStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期开始时间的日期字符串，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return
     */
    public static String getStartTimeOfDayStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.withHour(0).withMinute(0).withSecond(0), pattern);
    }

    /**
     * 获取今天结束时间的日期字符串
     *
     * @return
     */
    public static String getEndTimeOfDayStr() {
        return getEndTimeOfDayStr(LocalDateTime.now());
    }

    /**
     * 获取指定日期结束时间的日期字符串
     *
     * @param localDateTime 指定日期时间
     * @return
     */
    public static String getEndTimeOfDayStr(LocalDateTime localDateTime) {
        return getEndTimeOfDayStr(localDateTime, DATETIME_PATTERN);
    }

    /**
     * 获取指定日期结束时间的日期字符串，带日期格式化参数
     *
     * @param localDateTime 指定日期时间
     * @param pattern       日期时间格式
     * @return
     */
    public static String getEndTimeOfDayStr(LocalDateTime localDateTime, String pattern) {
        return format(localDateTime.withHour(23).withMinute(59).withSecond(59), pattern);
    }

    /**
     * 切割日期，按照周期切割成小段日期段
     *
     * @param startDate
     * @param endDate
     * @param period
     * @return
     */
    public static List<String> listDateStrs(String startDate, String endDate, String period) {
        List<String> result = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate end = LocalDate.parse(endDate, dateTimeFormatter);
        LocalDate start = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate tmp = start;
        switch (period) {
            case DAY:
                while (start.isBefore(end) || start.isEqual(end)) {
                    result.add(start.toString());
                    start = start.plusDays(1);
                }
                break;
            case WEEK:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    if (tmp.plusDays(6).isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + tmp.plusDays(6));
                    }
                    tmp = tmp.plusDays(7);
                }
                break;
            case MONTH:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfMonth = tmp.with(TemporalAdjusters.lastDayOfMonth());
                    if (lastDayOfMonth.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfMonth);
                    }
                    tmp = lastDayOfMonth.plusDays(1);
                }
                break;
            case YEAR:
                while (tmp.isBefore(end) || tmp.isEqual(end)) {
                    LocalDate lastDayOfYear = tmp.with(TemporalAdjusters.lastDayOfYear());
                    if (lastDayOfYear.isAfter(end)) {
                        result.add(tmp.toString() + "," + end);
                    } else {
                        result.add(tmp.toString() + "," + lastDayOfYear);
                    }
                    tmp = lastDayOfYear.plusDays(1);
                }
                break;
            default:
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getLocalDateTimeStr());
        System.out.println(getLocalDateStr());
        System.out.println(getLocalTimeStr());
        System.out.println(getDayOfWeekStr());
        System.out.println(getDayOfWeekStr(LocalDate.now()));

        System.out.println("===================");
        System.out.println(format(LocalDate.now(), UNSIGNED_DATE_PATTERN));

        System.out.println("===================");
        System.out.println(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN));
        System.out.println(parseLocalDate("2021-09-16", DATE_PATTERN));

        System.out.println("===================");
        System.out.println(plus(LocalDateTime.now(), 3, ChronoUnit.HOURS));
        System.out.println(minus(LocalDateTime.now(), 4, ChronoUnit.DAYS));

        System.out.println("===================");
        System.out.println(getChronoUnitBetween(LocalDateTime.now(), parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), ChronoUnit.MINUTES));
        System.out.println(getChronoUnitBetween(LocalDate.now(), parseLocalDate("2021-09-16", DATE_PATTERN), ChronoUnit.WEEKS));

        System.out.println("===================");
        System.out.println(getFirstDayOfYearStr());
        System.out.println(getFirstDayOfYearStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getFirstDayOfYearStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println(getLastDayOfYearStr());
        System.out.println(getLastDayOfYearStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getLastDayOfYearStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println("===================");
        System.out.println(getFirstDayOfMonthStr());
        System.out.println(getFirstDayOfMonthStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getFirstDayOfMonthStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println(getLastDayOfMonthStr());
        System.out.println(getLastDayOfMonthStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getLastDayOfMonthStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println("===================");
        System.out.println(getFirstDayOfWeekStr());
        System.out.println(getFirstDayOfWeekStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getFirstDayOfWeekStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println(getLastDayOfWeekStr());
        System.out.println(getLastDayOfWeekStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getLastDayOfWeekStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println("===================");
        System.out.println(getStartTimeOfDayStr());
        System.out.println(getStartTimeOfDayStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getStartTimeOfDayStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println(getEndTimeOfDayStr());
        System.out.println(getEndTimeOfDayStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN)));
        System.out.println(getEndTimeOfDayStr(parseLocalDateTime("2021-09-16 16:34:22", DATETIME_PATTERN), UNSIGNED_DATETIME_PATTERN));

        System.out.println("===================");
        List<String> dateStrs1 = listDateStrs("2019-01-30", "2020-12-13", MONTH);
        for (String dateStr : dateStrs1) {
            System.out.println(dateStr);
        }

        System.out.println("===================");
        List<String> dateStrs2 = listDateStrs("2020-12-01", "2020-12-13", DAY);
        for (String dateStr : dateStrs2) {
            System.out.println(dateStr);
        }

    }
}
