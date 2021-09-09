package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadShareVariableDemo
 * @Description
 * @Author H
 * @Date 2021/8/11 15:46
 * @Version 1.0
 */
public class ThreadShareVariableDemo {
    public static void main(String[] args) {
        Runnable runnable = new ShareVariableRunnable();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(runnable, "thread:" + (i + 1));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class ShareVariableRunnable implements Runnable {
    private int count = 5;

//    @Override
//    public void run() {
//        System.out.println("" + Thread.currentThread().getName() + ",count:" + count--);
//    }

    // 为了线程安全
    // 解决方案在访问变量方法中增加synchronized关键字
    @Override
    public synchronized void run() {
        System.out.println("" + Thread.currentThread().getName() + ",count:" + count--);
    }
}
