package com.example.springbootdemomytool.utils.threaddemo.threaddemo01;

/**
 * @ClassName ThreadVariableStopDemo
 * @Description
 * @Author H
 * @Date 2021/8/11 16:16
 * @Version 1.0
 */
public class ThreadVariableStopDemo {
    public static void main(String[] args) throws InterruptedException {
        VariableStopThread thread = new VariableStopThread("thread_1");
        thread.start();
        Thread.sleep(10);
         thread.Stop();
    }

}

class VariableStopThread extends Thread {
    private boolean interrupt = true;

    public VariableStopThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName: " + Thread.currentThread().getName() + ": 的线程开始运行！");
        int i = 0;
        while (interrupt) {
            System.out.println("" + (i++));
        }

        System.out.println("我停止了！timer: " + System.currentTimeMillis());
    }

    public void Stop() {
        System.out.println(Thread.currentThread().getName() + ": 线程设置了停止！");
        this.interrupt = false;
    }
}