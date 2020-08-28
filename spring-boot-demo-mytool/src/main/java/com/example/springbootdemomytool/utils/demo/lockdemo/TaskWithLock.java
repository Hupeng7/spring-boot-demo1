package com.example.springbootdemomytool.utils.demo.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TaskWithLock
 * @Description
 * @Author hup
 * @Date 2020/8/20 19:07
 * @Version 1.0
 */
public class TaskWithLock extends Task implements Runnable {
    // 声明显式锁
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            // 开始锁定
            lock.lock();
            doSomething();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
