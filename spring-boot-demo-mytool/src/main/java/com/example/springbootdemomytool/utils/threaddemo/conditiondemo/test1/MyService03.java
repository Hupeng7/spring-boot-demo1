package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService03
 * @Description 使用多个Condition实现通知部分线程：正确用法
 * @Author H
 * @Date 2021/8/6 14:11
 * @Version 1.0
 */
public class MyService03 {
    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA() {
        lock.lock();
        try {
            System.out.println("begin awaitA 时间为： " + System.currentTimeMillis() +
                    " ThreadName = " + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end awaitA 时间为： " + System.currentTimeMillis() +
                    " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        lock.lock();
        try {
            System.out.println("begin awaitB 时间为： " + System.currentTimeMillis() +
                    " ThreadName = " + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end awaitB 时间为： " + System.currentTimeMillis() +
                    " ThreadName = " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A() {
        lock.lock();
        try {
            System.out.println("signalAll A时间为： " + System.currentTimeMillis() +
                    " ThreadName = " + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B() {
        lock.lock();
        try {
            System.out.println("signalAll B时间为： " + System.currentTimeMillis() +
                    "ThreadName = " + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }


}
