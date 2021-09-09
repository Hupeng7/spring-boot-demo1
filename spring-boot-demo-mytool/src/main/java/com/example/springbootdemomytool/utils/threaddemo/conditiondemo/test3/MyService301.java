package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService301
 * @Description
 * @Author H
 * @Date 2021/8/6 15:44
 * @Version 1.0
 */
public class MyService301 {
    private ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set() {
        lock.lock();
        try {
            while (hasValue == true) {
                System.out.println("★★");
                condition.await();
            }
            System.out.println("★");
            hasValue = true;
            condition.signal();
            //condition.signalAll();
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
                System.out.println("☆☆");
                condition.await();
            }
            System.out.println("☆");
            hasValue = false;
            condition.signal();
            //condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
