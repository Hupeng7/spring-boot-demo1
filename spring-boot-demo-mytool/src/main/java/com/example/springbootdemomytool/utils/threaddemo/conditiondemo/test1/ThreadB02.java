package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName ThreadB02
 * @Description
 * @Author H
 * @Date 2021/8/6 13:59
 * @Version 1.0
 */
public class ThreadB02 extends Thread {
    private MyService02 myService02;

    public ThreadB02(MyService02 myService02) {
        this.myService02 = myService02;
    }

    @Override
    public void run() {
        myService02.awaitB();
    }
}
