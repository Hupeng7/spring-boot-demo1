package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test2;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName WaitingTime
 * @Description 线程不断休眠
 * @Author H
 * @Date 2022/10/28 17:10
 * @Version 1.0
 */
public class WaitingTime implements Runnable {
    @Override
    public void run() {
        while (true) {
            waitSecond(200);
        }
    }

    // 线程等待多少秒
    public static final void waitSecond(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
