package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test2;

/**
 * @ClassName WaitingState
 * @Description
 * @Author H
 * @Date 2022/10/28 17:13
 * @Version 1.0
 */
public class WaitingState implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (WaitingState.class) {
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
