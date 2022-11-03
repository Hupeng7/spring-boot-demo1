package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test3;

/**
 * @ClassName ThreadSort01
 * @Description 不是顺序的
 * 注意：每个人运行的情况可能都不一样。
 * 可以看到，每次运行程序时，线程的执行顺序可能不同。线程的启动顺序并不能决定线程的执行顺序。
 * @Author H
 * @Date 2022/10/31 10:51
 * @Version 1.0
 */
public class ThreadSort01 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("thread3");
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
