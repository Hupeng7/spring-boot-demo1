package com.example.springbootdemomytool.utils.testdemo;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * created by yinwc at 2018/12/5 005 15:06
 */
@Slf4j
public class DateUtil {
    //获取当前时间第n天前的开始时间，如果n为负数，则实际为第n天后
    public static Date getStartTime(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -n);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    //获取当前时间第n天前的结束时间，如果n为负数，则实际为第n天后
    public static Date getEndTime(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -n);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static Date parseDate(String sDate, String formate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /*public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println("startTime = " + sdf.format(getStartTime(1)));
        System.out.println("endTime = " + sdf.format(getEndTime(1)));
    }*/

    /**
     * 获取两个时间相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static Date getDateFormat(Date date, String format) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDateFormatString(Date date, String formate) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate);
        return simpleDateFormat.format(date);
    }

    public static String getDefaultFormat(Date date) {
        return getDateFormatString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 给定日期的前n年
     */
    public static Date getDateYearsAgo(Date baseDate, int years) {
        // 1. 将Date转换为LocalDate
        LocalDate localBase = baseDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // 2. 计算n年前的日期
        LocalDate result = localBase.minusYears(years);

        // 3. 处理闰年特殊情况（2月29日）
        if (localBase.getMonthValue() == 2 &&
                localBase.getDayOfMonth() == 29 &&
                !result.isLeapYear()) {
            result = LocalDate.of(result.getYear(), 2, 28);
        }

        // 4. 转换回Date类型
        return Date.from(result.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    public static void main(String[] args) {

        // 当前日期 3 年前
        Date date3YearsAgo = DateUtil.getDateYearsAgo(new Date(), 3);
        // 含左不含右 [2022-09-15,2022-09-16)
        Date taskStartTime = DateUtil.getDateFormat(date3YearsAgo, "yyyy-MM-dd");


        log.info("taskStartTime: {},taskEndTime: {}", DateUtil.getDefaultFormat(taskStartTime));
    }

}
