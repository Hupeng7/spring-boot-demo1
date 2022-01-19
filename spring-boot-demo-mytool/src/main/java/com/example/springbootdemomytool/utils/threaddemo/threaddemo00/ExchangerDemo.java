package com.example.springbootdemomytool.utils.threaddemo.threaddemo00;

import java.util.concurrent.Exchanger;

/**
 * @ClassName ExchangerDemo
 * @Description
 * @Author H
 * @Date 2021/12/28 17:55
 * @Version 1.0
 */
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("这是线程A,得到了另一个线程的数据" + exchanger.exchange("这是来自线程A的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("这时候线程A是阻塞的，在等待线程B的数据");
        Thread.sleep(1000);

        new Thread(()->{
            try {
                System.out.println("这是线程B,得到了另一个线程的数据："+exchanger.exchange("这是来自线程B的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
