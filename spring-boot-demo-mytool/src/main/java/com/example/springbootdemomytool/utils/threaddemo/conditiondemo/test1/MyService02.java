package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService02
 * @Description 使用多个Condition实现通知部分线程:错误用法
 * @Author H
 * @Date 2021/8/6 13:43
 * @Version 1.0
 */
public class MyService02 {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void awaitA() {
        lock.lock();
        try {
            System.out.println("begin awaitA 时间为: " + System.currentTimeMillis()
                    + " ThreadName = " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end awaitA 时间为： " + System.currentTimeMillis()
                    + " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        lock.lock();
        try {
            System.out.println("begin awaitB 时间为 " + System.currentTimeMillis()
                    + " ThreadName = " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end awaitB 时间为 " + System.currentTimeMillis()
                    + " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll() {
        lock.lock();
        try {
            System.out.println("signalAll 时间为： " + System.currentTimeMillis()
                    + " ThreadName = " + Thread.currentThread().getName());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}
