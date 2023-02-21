package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test7;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName TimerTest
 * @Description
 * @Author H
 * @Date 2022/11/8 15:12
 * @Version 1.0
 */
public class TimerTest {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("测试Timer类");
            }
        }, 1000, 1000);
        Thread.sleep(10000);
        timer.cancel();
    }
}
