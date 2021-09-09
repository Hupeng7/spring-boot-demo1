package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName ThreadB02
 * @Description
 * @Author H
 * @Date 2021/8/6 13:59
 * @Version 1.0
 */
public class ThreadB03 extends Thread {
    private MyService03 myService03;

    public ThreadB03(MyService03 myService03) {
        this.myService03 = myService03;
    }

    @Override
    public void run() {
        myService03.awaitB();
    }
}
