package com.example.springbootdemomytool.utils.serializabledemo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronizeAndClassLock
 * @Description synchronized修饰同一个类的两个静态方法时互斥
 *         https://mp.weixin.qq.com/s/nhH4ihPdu9fyZxHwExLTNg
 * @Author H
 * @Date 2021/4/15 14:49
 * @Version 1.0
 */
public class SynchronizeAndClassLock {
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            // new 了一个Classlock对象
            // new ClassLock();
            ClassLock.test1();
        }).start();

        new Thread(() -> {
            ClassLock.test2();
        }).start();


    }

}

class ClassLock {
    public synchronized static void test1() {
        System.out.println(new Date() + " " + Thread.currentThread().getName() + " begin...");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        System.out.println(new Date() + " " + Thread.currentThread().getName() + " end...");
    }

    // 【注意】 public static void test2(){} 不会互斥，因为此时test2没有使用类锁
    public synchronized static void test2() {
        System.out.println(new Date() + " " + Thread.currentThread().getName() + " begin...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        System.out.println(new Date() + " " + Thread.currentThread().getName() + " end...");
    }


}
