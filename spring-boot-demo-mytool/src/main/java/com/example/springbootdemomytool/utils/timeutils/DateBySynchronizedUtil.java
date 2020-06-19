package com.example.springbootdemomytool.utils.timeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @ClassName DateBySynchronizedUtil
 * @Description
 * 线程安全- SimpleDateFormat.format()
 * 加一把线程同步锁：synchronized(lock)
 * 缺点：性能较差，每次都要等待锁释放后其他线程才能进入
 * @Author Leo
 * @Date 2020/5/22 15:21
 * @Version 1.0
 */
public class DateBySynchronizedUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    private static String date[] = {"01-Jan-1999", "01-Jan-2000", "01-Jan-2001"};

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
                            String str2 = format.format(format.parse(str1));
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
                            synchronized (format) { // 此处线程同步锁
                                String str1 = date[temp];
                                String str2 = format.format(format.parse(str1));
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


}
