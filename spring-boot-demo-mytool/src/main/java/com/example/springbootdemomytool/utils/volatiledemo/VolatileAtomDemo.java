package com.example.springbootdemomytool.utils.volatiledemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName VolatileAtomDemo
 * @Description
 * @Author H
 * @Date 2021/12/15 17:59
 * @Version 1.0
 */
public class VolatileAtomDemo {
    // 1. volatitle 不保证原子性
    // 原子性：保证数据一致性、完整性
    volatile int number = 0;
//    public void addPlusPlus() {
//        number++;
//    }

    // 2. 保证原子性 synchronized
//    public synchronized void addPlusPlus() {
//        number++;
//    }

    // 3. 加锁解决 保证原子性
    Lock lock = new ReentrantLock();
    public void addPlusPlus() {
        lock.lock();
        try {
            number++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        VolatileAtomDemo volatileAtomDemo = new VolatileAtomDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    volatileAtomDemo.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 后台默认两个线程，一个是main线程，一个是gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // 如果volatile保证原子性的话，最终的结果应该是20000
        // 但是每次程序执行结果都不等于20000
        System.out.println(Thread.currentThread().getName() + "\t final number result = " + volatileAtomDemo.number);
    }

}
