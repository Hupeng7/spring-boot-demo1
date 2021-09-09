package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadCreateDemo1
 * @Description 创建线程
 *  https://www.jianshu.com/p/d901b25e0d4a
 * @Author H
 * @Date 2021/8/11 15:11
 * @Version 1.0
 */
public class ThreadCreateDemo1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("hello_world!");
        }
    }
}
