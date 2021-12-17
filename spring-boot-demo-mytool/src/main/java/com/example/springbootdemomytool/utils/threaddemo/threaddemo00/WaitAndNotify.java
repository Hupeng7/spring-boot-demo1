package com.example.springbootdemomytool.utils.threaddemo.threaddemo00;

/**
 * @ClassName WaitAndNotify
 * @Description lock.wait() 让⾃⼰进⼊等待状态 锁释放
 * ⽤ notify() ⽅法叫醒另⼀个正在等待的线程，然后⾃⼰使⽤ wait() ⽅法陷⼊等待 并释放 lock 锁
 * @Author H
 * @Date 2021/12/17 15:22
 * @Version 1.0
 */
public class WaitAndNotify {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("Thread A, do some business ... " + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("Thread B, do some business ... " + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }


}
