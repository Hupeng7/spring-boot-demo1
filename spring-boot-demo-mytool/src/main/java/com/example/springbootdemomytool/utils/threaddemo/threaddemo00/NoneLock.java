package com.example.springbootdemomytool.utils.threaddemo.threaddemo00;

/**
 * @ClassName NoneLock
 * @Description 线程A和线程B各⾃独⽴⼯作，输出⾃⼰的打印值。
 * 乱序
 * @Author H
 * @Date 2021/12/17 15:11
 * @Version 1.0
 */
public class NoneLock {

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread A" + i);
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread B " + i);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }


}
