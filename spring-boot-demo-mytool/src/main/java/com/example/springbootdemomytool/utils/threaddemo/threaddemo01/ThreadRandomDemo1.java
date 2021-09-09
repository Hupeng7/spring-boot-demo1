package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadRandomDemo1
 * @Description 线程运行结果与执行顺序无关
 *  线程的调度是由CPU决定，CPU执行子任务时间具有不确定性。
 * @Author H
 * @Date 2021/8/11 15:23
 * @Version 1.0
 */
public class ThreadRandomDemo1 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new RandomThread("randomThread: " + i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}

class RandomThread extends Thread {
    public RandomThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("ThreadName: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
