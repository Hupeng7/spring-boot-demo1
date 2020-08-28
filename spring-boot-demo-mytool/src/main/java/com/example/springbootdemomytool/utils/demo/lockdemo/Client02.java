package com.example.springbootdemomytool.utils.demo.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Client02
 * @Description 显式锁与内部锁的不同
 * 1.Lock支持更细粒度的锁控制
 * 2.Lock是无阻塞锁，synchronized是阻塞锁
 * 3.Lock可实现公平锁，synchronized只能是非公平锁
 * 4.Lock是代码级别的，synchronized是JVM级的
 * @Author hup
 * @Date 2020/8/21 10:09
 * @Version 1.0
 */
public class Client02 {
    public static void main(String[] args) {
        // 多个线程共享锁
        final Lock lock = new ReentrantLock();
        // 启动三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        Thread.sleep(2000L);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }

    }

}
