package com.example.springbootdemomytool.utils.phaserdemo;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PhaserExample
 * @Description 阶段器
 * @Author H
 * @Date 2021/6/29 15:17
 * @Version 1.0
 */
public class PhaserExample {
    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        // 创建5个任务
        for (int i = 0; i < 5; i++) {
            new Task(phaser).start();
        }
        // 动态注册
        phaser.register();
        // 等待其他线程完成工作
        phaser.arriveAndAwaitAdvance();
        System.out.println("All of worker finished the task");

    }

    private static class Task extends Thread {
        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            // 动态注册任务
            this.phaser.register();
        }

        @Override
        public void run() {
            try {
                System.out.println("The thread [" + getName() + "] is working");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The thread [" + getName() + "] work finished");
            // 等待其他线程池完成工作
            phaser.arriveAndAwaitAdvance();

        }
    }


}
