package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockExample3
 * @Description
 * @Author H
 * @Date 2022/11/11 16:41
 * @Version 1.0
 */
@Slf4j
public class LockExample3 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            reentrantLock.lock(); // 1
            try {
                log.info("wait signal 111");
                condition.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                log.info("get signal  444"); // 4
            }
        }, "线程一").start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock 222"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal 333");
            reentrantLock.unlock();  // 3
        }, "线程二").start();
    }


}
