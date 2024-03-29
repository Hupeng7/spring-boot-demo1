package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test3;

/**
 * @ClassName ThreadSort02
 * @Description 顺序执行
 * @Author H
 * @Date 2022/10/31 10:57
 * @Version 1.0
 */
public class ThreadSort02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("thread1");
        });

        Thread thread2 = new Thread(()->{
            System.out.println("thread2");
        });

        Thread thread3 = new Thread(()->{
            System.out.println("thread3");
        });

        thread1.start();
        // 实际上让主线程等待子线程执行完成
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }
}
