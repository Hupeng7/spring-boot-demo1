package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService01
 * @Description
 * @Author H
 * @Date 2021/8/6 11:51
 * @Version 1.0
 */
public class MyService01 {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await() {
        lock.lock();
        try {
            System.out.println("await 时间为：" + System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        lock.lock();
        try {
            System.out.println("singal 时间为：" + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}
