package com.example.springbootdemomytool.utils.async.asyncDemo1;

/**
 * @ClassName AsyncThread
 * @Description
 * @Author H
 * @Date 2022/7/29 10:55
 * @Version 1.0
 */
public class AsyncThread extends Thread {
    @Override
    public void run() {
        System.out.println("当前线程名称: " + this.getName() + ",执行线程名称: " + Thread.currentThread().getName() + "-hello");
    }

    public static void main(String[] args) {
        // 模拟业务线程
        // .......

        // 创建异步线程
        AsyncThread asyncThread = new AsyncThread();

        // 启动异步线程
        asyncThread.start();
    }
}
