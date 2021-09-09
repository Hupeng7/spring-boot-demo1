package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadVariableStopDemo
 * @Description
 * @Author H
 * @Date 2021/8/11 16:16
 * @Version 1.0
 */
public class ThreadVariableStopDemo1 {
    public static void main(String[] args) throws InterruptedException {
        VariableStopThread1 thread = new VariableStopThread1("thread_1");
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }

}

class VariableStopThread1 extends Thread {
    private boolean interrupt = true;

    public VariableStopThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName: " + Thread.currentThread().getName() + ": 的线程开始运行！");

        while (!isInterrupted()) {
        }
        System.out.println("我停止了！timer: " + System.currentTimeMillis());
    }

}