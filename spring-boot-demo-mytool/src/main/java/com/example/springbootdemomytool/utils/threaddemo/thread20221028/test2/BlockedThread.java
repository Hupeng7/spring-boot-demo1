package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test2;

/**
 * @ClassName BlockedThread
 * @Description
 * @Author H
 * @Date 2022/10/28 17:24
 * @Version 1.0
 */
public class BlockedThread implements Runnable {
    @Override
    public void run() {
        synchronized (BlockedThread.class) {
            while (true) {
                WaitingTime.waitSecond(100);
            }
        }
    }
}
