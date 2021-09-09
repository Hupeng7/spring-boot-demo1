package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName ThreadA01
 * @Description
 * @Author H
 * @Date 2021/8/6 12:03
 * @Version 1.0
 */
public class ThreadA01 extends Thread {
    private MyService01 myService01;

    public ThreadA01(MyService01 myService01) {
        this.myService01 = myService01;
    }

    @Override
    public void run() {
        myService01.await();
    }
}
