package com.example.springbootdemomytool.utils.threadpooldemo.test3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatchTest
 * @Description
 * @Author H
 * @Date 2024/8/7 17:23
 * @Version 1.0
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        //monkeyEatAwait();
        monkeyEatAwaitPar();
    }

    public static void monkeyEatAwait() {
        try {
            // 倒数器计数
            CountDownLatch countDownLatch = new CountDownLatch(1);
            System.out.println("monkey eat banana ready--->");

            // 倒数3个数
            for (int i = 3; i > 0; i--) {
                System.out.println("倒数: " + i);
                TimeUnit.SECONDS.sleep(1);
            }
            countDownLatch.countDown();
            countDownLatch.await();
            System.out.println("boom start!");

            int monkeyNum = 5;
            CountDownLatch countDownLatchMonkey = new CountDownLatch(monkeyNum);
            for (int i = 0; i < monkeyNum; i++) {
                new MonkeyBanana("猴哥" + (i + 1), countDownLatchMonkey).start();
            }
            // 等待所有猴子都吃完香蕉
            countDownLatchMonkey.await();
            System.out.println("all monkey already eat banana!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void monkeyEatAwaitPar(){
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            System.out.println("monkey eat banana ready--->");

            for (int i = 3; i > 0; i--) {
                System.out.println("倒数: "+i);
                TimeUnit.SECONDS.sleep(1);
            }

            countDownLatch.countDown();
            countDownLatch.await();
            System.out.println("boom start!");

            int monkeyNum = 5;
            CountDownLatch countDownLatchMonkey = new CountDownLatch(monkeyNum);
            for (int i = 0; i < monkeyNum; i++) {
                new MonkeyBanana("猴哥" + (i + 1), countDownLatchMonkey).start();
            }

            // 超时判断
            boolean await = countDownLatchMonkey.await(5,TimeUnit.SECONDS);
            // 超过5秒结束比赛
            if(!await){
                System.out.println("time is over!");
            }else{
                System.out.println("所有猴子都吃完了香蕉!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
