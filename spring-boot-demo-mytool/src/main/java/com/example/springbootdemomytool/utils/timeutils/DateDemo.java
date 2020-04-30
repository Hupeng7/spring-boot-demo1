package com.example.springbootdemomytool.utils.timeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/22 10:33
 * @Version 1.0
 */
public class DateDemo {

    public static void main(String[] args) {
//        Date d = parseDate("2020/4/18", "yyyy/MM/dd");
//        System.out.println("d---" + d);
        System.out.println(convert2theN("2020-5-7", -11,"yyyy-MM-dd"));
    }

    public static String getN2CurrentTime(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(getDateOfN2CurrentTime(n));
    }

    public static Date getDateOfN2CurrentTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, n); //here Calendar.DAY_OF_MONTH have same result
        return calendar.getTime();
    }

    public static Date parseDate(String sDate, String formate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
    }

    //将 dueDate = yyyy-MM-dd 的日期格式，转化为 dateFormat，然后 提前+[-] n天
    public static String convert2theN(String dueDate, int n, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date var1 = sdf.parse(dueDate);
            Calendar c = Calendar.getInstance();
            c.setTime(var1);
            c.add(Calendar.DAY_OF_YEAR, n);

            return sdf.format(c.getTime());
        } catch (Exception e) {
            System.out.println("found exception: " + e.getMessage());
            return null;
        }
    }
}
