package com.example.springbootdemomytool.utils.timeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateCompare
 * @Description 比较时间大小
 * @Author hup
 * @Date 2020/11/6 9:40
 * @Version 1.0
 */
public class DateCompare {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public boolean compare(String time1, String time2) throws ParseException {
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //将字符串形式的时间转化为Date类型的时间
        Date a = sdf.parse(time1);
        Date b = sdf.parse(time2);

        //方法一：
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if (a.before(b)) {
            return true;
        } else {
            return false;
        }


        //方法二：
        /*
         * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒
        if(a.getTime()-b.getTime()<0)
            return true;
        else
            return false;
        */
    }

    public static void main(String[] args) throws Exception {
        boolean result = new DateCompare().compare("2012-11-30 16:11:16", "2012-11-30 16:18:18");
        System.out.println(result);

        Date date1 = sdf.parse("2012-11-30 16:11:16");
        Date date2 = sdf.parse("2012-11-30 00:00:00");
        Date date3 = sdf.parse("2012-11-20 00:00:00");
        Date date4 = sdf.parse("2012-12-25 00:00:00");
        Date date5 = sdf.parse("2012-12-25 00:00:00");
        System.out.println("===================compareTo===================");
        System.out.println("前者大于后者" + dateCompareTo(date1, date2));
        System.out.println("前者小于后者" + dateCompareTo(date3, date2));
        System.out.println("前者大于后者" + dateCompareTo(date4, date2));
        System.out.println("前者大于后者" + dateCompareTo(date4, date1));
        System.out.println("前者等于后者" + dateCompareTo(date4, date5));

        System.out.println("===================before===================");
        System.out.println("前者大于后者" + dateBefore(date1, date2));
        System.out.println("前者小于后者" + dateBefore(date3, date2));
        System.out.println("前者大于后者" + dateBefore(date4, date2));
        System.out.println("前者大于后者" + dateBefore(date4, date1));
        System.out.println("前者等于后者" + dateBefore(date4, date5));

        System.out.println("===================parseToTimestamp===================");
        System.out.println("前者大于后者" + parseToTimestamp(date1, date2));
        System.out.println("前者小于后者" + parseToTimestamp(date3, date2));
        System.out.println("前者大于后者" + parseToTimestamp(date4, date2));
        System.out.println("前者大于后者" + parseToTimestamp(date4, date1));
        System.out.println("前者等于后者" + parseToTimestamp(date4, date5));


    }

    /**
     * 出现三种情况
     * 1. 前者大于后者 返回1
     * 2. 前者小于后者 返回-1
     * 3. 前者等于后者 返回0
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int dateCompareTo(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    /**
     * 通过before方法（不能比较等于）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dateBefore(Date date1, Date date2) {
        return date1.before(date2);
    }

    public static int parseToTimestamp(Date date1, Date date2) {
        long date1Timestamp = date1.getTime();
        long date2Timestamp = date2.getTime();
        if (date1Timestamp > date2Timestamp) {
            return 1;
        } else if (date1Timestamp < date2Timestamp) {
            return -1;
        } else {
            return 0;
        }
    }


}
