package com.example.springbootdemomytool.utils.timeutils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateFormatUtils
 * @Description 正确的建议用yyyyMMdd: 就是正常的时间表示
 * YYYYMMdd：表示当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，那么这周就算入下一年
 * yyyy-MM-dd
 * 2019-12-28: 2019-12-28
 * 2019-12-29: 2019-12-29
 * 2019-12-31: 2019-12-31
 * 2020-01-01: 2020-01-01
 * YYYY-MM-dd
 * 2019-12-28: 2019-12-28
 * 2019-12-29: 2020-12-29   ×
 * 2019-12-31: 2020-12-31   ×
 * 2020-01-01: 2020-01-01
 * @Author Leo
 * @Date 2020/4/3 10:56
 * @Version 1.0
 */
public class DateFormatUtils {

    public static void main(String[] args) {

        //YYYY 是表示：当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，那么这周就算入下一年。
        //2019-12-29至2020-1-4跨年周
        Calendar calendar = Calendar.getInstance();
        // 2019-12-28
        calendar.set(2019, Calendar.DECEMBER, 28);
        Date strDate1 = calendar.getTime();
        // 2019-12-29
        calendar.set(2019, Calendar.DECEMBER, 29);
        Date strDate2 = calendar.getTime();
        // 2019-12-31
        calendar.set(2019, Calendar.DECEMBER, 31);
        Date strDate3 = calendar.getTime();
        // 2020-01-01
        calendar.set(2020, Calendar.JANUARY, 1);
        Date strDate4 = calendar.getTime();

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd");

        // yyyy-MM-dd
        System.out.println("yyyy-MM-dd");
        System.out.println("2019-12-28: " + df1.format(strDate1));
        System.out.println("2019-12-29: " + df1.format(strDate2));
        System.out.println("2019-12-31: " + df1.format(strDate3));
        System.out.println("2020-01-01: " + df1.format(strDate4));

        // YYYY-MM-dd
        System.out.println("YYYY-MM-dd");
        System.out.println("2019-12-28: " + df2.format(strDate1));
        System.out.println("2019-12-29: " + df2.format(strDate2));
        System.out.println("2019-12-31: " + df2.format(strDate3));
        System.out.println("2020-01-01: " + df2.format(strDate4));

    }


}
