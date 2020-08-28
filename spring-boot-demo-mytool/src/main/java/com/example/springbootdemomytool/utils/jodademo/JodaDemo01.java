package com.example.springbootdemomytool.utils.jodademo;

import org.joda.time.DateTime;

/**
 * @ClassName JodaDemo01
 * @Description TODO Joda 工具类
 * @Author hup
 * @Date 2020/8/25 15:13
 * @Version 1.0
 */
public class JodaDemo01 {
    public static void main(String[] args) {
        System.out.println("当前时间10天之后 :" + afterNdays(10));
        System.out.println("当前时间最小日期：" + currentWeekMinDay() + "，最大日期：" + currentWeekMaxDay());
        System.out.println("current month min date :" + currentMonthMinDay() + ", max date:" + currentMonthMaxDay());
    }

    /**
     * 当前时间n天之后的日期
     *
     * @param days
     * @return
     */
    public static String afterNdays(int days) {
        String s = DateTime.now().dayOfYear().addToCopy(days).toString("yyyy-MM-dd");
        return s;
    }

    /**
     * 当周的周一
     *
     * @return
     */
    public static String currentWeekMinDay() {
        String monday = DateTime.now().dayOfWeek().withMinimumValue().toString("yyyy-MM-dd");
        return monday;
    }

    /**
     * 当周的周日
     *
     * @return
     */
    public static String currentWeekMaxDay() {
        String sunday = DateTime.now().dayOfWeek().withMaximumValue().toString("yyyy-MM-dd");
        return sunday;
    }

    /**
     * 当月最小日期
     *
     * @return
     */
    public static String currentMonthMinDay() {
        String result = DateTime.now().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        return result;
    }

    /**
     * 当月最大日期
     *
     * @return
     */
    public static String currentMonthMaxDay() {
        String result = DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd");
        return result;
    }


}
