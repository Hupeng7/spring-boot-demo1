package com.example.springbootdemomytool.utils.threadpooldemo.test3;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName MonkeyBanana
 * @Description
 * @Author H
 * @Date 2024/8/7 17:05
 * @Version 1.0
 */
public class MonkeyBanana extends Thread {
    String name;
    CountDownLatch countDownLatch;

    public MonkeyBanana(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(name + " start eat banana");
        try {
            long sleepTime = (long) (Math.random() * 10000);
            Thread.sleep(sleepTime);
            long speedTime = sleepTime / 1000;
            if (speedTime == 0) {
                System.out.println(name + "简直不要太厉害，跟我们表演了一波一口闷香蕉");
            } else if (speedTime > 0 && speedTime < 5) {
                System.out.println(name + "在" + speedTime + "秒内吃完了香蕉");
            } else if (speedTime == 5) {
                System.out.println(name + "在最后一秒内吃完了香蕉");
            } else if (speedTime > 5) {
                System.out.println(name + "在" + speedTime + "秒内吃完了香蕉，已超时");
            } else {
                System.out.println(name + "时间不对");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程倒数器，每次都会减1
            countDownLatch.countDown();
        }
    }

}
