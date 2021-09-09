package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadCreateDemo2
 * @Description 创建线程
 * @Author H
 * @Date 2021/8/11 15:15
 * @Version 1.0
 */
public class ThreadCreateDemo2 {
    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 建议使用实现Runnable接口方式
     * 解决单继承的局限性
     */
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("通过Runnable创建的线程！");
        }
    }
}
