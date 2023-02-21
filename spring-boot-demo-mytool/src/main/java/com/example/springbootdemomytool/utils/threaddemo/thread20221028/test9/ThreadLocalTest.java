package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test9;

/**
 * @ClassName ThreadLocalTest
 * @Description
 * @Author H
 * @Date 2022/11/11 18:03
 * @Version 1.0
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 创建第一个线程
        Thread threadA = new Thread(() -> {
            threadLocal.set("ThreadA: " + Thread.currentThread().getName());
            System.out.println("线程A本地变量中的值为: " + threadLocal.get());
        });

        // 创建第二个线程
        Thread threadB = new Thread(() -> {
            threadLocal.set("ThreadB: " + Thread.currentThread().getName());
            System.out.println("线程B本地变量中的值为: " + threadLocal.get());
        });

        // 启动线程A和线程B
        threadA.start();
        threadB.start();
    }
}
