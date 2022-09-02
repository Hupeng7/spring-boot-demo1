package com.example.springbootdemomytool.utils.volatiledemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName VolatoleAtomicityDemo
 * @Description
 * @Author H
 * @Date 2022/9/1 17:11
 * @Version 1.0
 */
public class VolatoleAtomicityDemo {
    public volatile static int inc = 0;

    public void increase() {
        inc++;
    }

    // 改进1
    public synchronized void increase1() {
        inc++;
    }

    // 改进2
    public static AtomicInteger inc2 = new AtomicInteger();

    public void increase2() {
        inc2.getAndIncrement();
    }

    // 改进3
    Lock lock = new ReentrantLock();
    public void increase3() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatoleAtomicityDemo volatoleAtomicityDemo = new VolatoleAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    volatoleAtomicityDemo.increase2();
                }
            });
        }
        // 等待1.5秒 保证上面程序执行完成
        Thread.sleep(1500);
        System.out.println(inc);
        System.out.println(inc2);
        threadPool.shutdown();
    }


}
