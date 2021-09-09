package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadPriorityDemo
 * @Description
 * @Author H
 * @Date 2021/8/11 17:23
 * @Version 1.0
 */
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Thread thread = new ThreadPriority("thread_1<<<<");
        Thread thread1 = new ThreadPriority(">>>>thread_2");
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread.start();
    }
}

class ThreadPriority extends Thread {
    public ThreadPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("" + Thread.currentThread().getName() + ",number:" + i + ",Priority:" +
                    Thread.currentThread().getPriority());
        }
    }
}
