package com.example.springbootdemomytool.utils.phaserdemo;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PhaserExample2
 * @Description
 * @Author H
 * @Date 2021/6/29 15:36
 * @Version 1.0
 */
public class PhaserExample2 {

    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        // 初始化5个pharties
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            new Athlete(phaser, i).start();
        }

    }

    // 创建运动员类
    private static class Athlete extends Thread {
        private Phaser phaser;
        private int no;

        public Athlete(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {
            try {
                System.out.println(no + ": 当前处于第：" + phaser.getPhase() + "阶段");
                System.out.println(no + ": start running");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end running");
                // 等待其他运动员完成跑步
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": 当前处于第：" + phaser.getPhase() + "阶段");
                System.out.println(no + ": start bicycle");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end bicycle");
                // 等待其他运动员完成骑行
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + "：当前处于第：" + phaser.getPhase() + "阶段");
                System.out.println(no + ": start long jump");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println(no + ": end long jump");
                // 等待其他运动员完成跳远
                phaser.arriveAndAwaitAdvance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
