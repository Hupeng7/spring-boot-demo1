package com.example.springbootdemomytool.utils.timeutils;

import netscape.security.UserTarget;
import org.junit.Test;
import org.omg.SendingContext.RunTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName CompareSDFAndDTF
 * @Description 解决SimpleDateFormat线程不安全
 * 2.1 将SimpleDateFormat定义成局部变量：
 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
 * String str1 = "01-Jan-2010";
 * String str2 = sdf.format(sdf.parse(str1));
 * 缺点：每调用一次方法就会创建一个SimpleDateFormat对象，方法结束又要作为垃圾回收
 * <p>
 * 2.2 加一把线程同步锁：synchronized(lock)
 * 缺点：性能较差，每次都要等待锁释放后其他线程才能进入
 * <p>
 * 2.3 使用ThreadLocal
 * 每个线程都将拥有自己的SimpleDateFormat对象副本。
 * <p>
 * <p>
 * 链接：https://www.jianshu.com/p/b212afa16f1f
 * @Author Leo
 * @Date 2020/5/22 10:06
 * @Version 1.0
 */
public class CompareSDFAndDTF {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    private static String[] date = {"01-Jan-1999", "09-Jan-2000", "07-Jan-2002", "06-Jan-2003", "05-Jan-2004", "04-Jan-2005", "03-Jan-2006", "02-Jan-2007"};

    public static void simpleDateFormat1() {
        System.out.println(format.format(new Date()) + " Exception made...");

    }

    public static void main(String[] args) {
        simpleDateFormat1();

        simpleDateFormatTest();
    }

    public static void simpleDateFormatTest() {
//        Exception in thread "Thread-3" Exception in thread "Thread-6" Exception in thread "Thread-5" Exception in thread "Thread-2" java.lang.RuntimeException: Thread-5, Expected 04-Jan-2005 but got 25-Dec-2006
//        at com.example.springbootdemomytool.utils.timeutils.CompareSDFAndDTF$1.run(CompareSDFAndDTF.java:46)
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str1 = date[temp];
                            String str2 = format1.format(format1.parse(str1));
                            System.out.println(Thread.currentThread().getName() + ", " + str1 + ", " + str2);
                            if (!str1.equals(str2)) {
                                throw new RuntimeException(Thread.currentThread().getName() + ", Expected " + str1 + " but got " + str2);
                            }
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException("parse failed ", e);
                    }
                }
            });
            // t1.start(); // 线程不安全  报错

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            synchronized (format1) { // 此处线程同步锁
                                String str1 = date[temp];
                                String str2 = format1.format(format1.parse(str1));
                                System.out.println(Thread.currentThread().getName() + ", " + str1 + ", " + str2);
                                if (!str1.equals(str2)) {
                                    throw new RuntimeException(Thread.currentThread().getName() + ",Expected " + str1 + " but,got " + str2);
                                }
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed ", e);
                    }
                }
            });
            // t2.start(); // 加一把线程同步锁 线程安全
            // 缺点：性能较差，每次都要等待锁释放后其他线程才能进入

        }
    }


    /**
     * 把当前时间格式为指定格式
     */
    @Test
    public void test5() {
        //获得当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = ldt.format(dtf);
        System.out.println(format);
    }

    /**
     * 把指定字符串格式化为日期
     */
    @Test
    public void test6() {
        String str1 = "2018-07-05 12:24:12";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str1, dtf);
        System.out.println(parse);
    }

}
