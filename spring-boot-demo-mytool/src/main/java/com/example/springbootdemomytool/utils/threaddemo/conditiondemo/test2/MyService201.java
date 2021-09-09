package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService201
 * @Description
 * @Author H
 * @Date 2021/8/6 15:20
 * @Version 1.0
 */
public class MyService201 {
    private ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {
        lock.lock();
        try {
            while (hasValue == true) {
                condition.await();
            }
            System.out.println("★");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            while (hasValue == false) {
                condition.await();
            }
            System.out.println("☆");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
