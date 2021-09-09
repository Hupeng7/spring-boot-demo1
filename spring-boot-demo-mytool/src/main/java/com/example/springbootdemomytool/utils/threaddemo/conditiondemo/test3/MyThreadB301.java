package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test3;

/**
 * @ClassName MyThreadB301
 * @Description
 * @Author H
 * @Date 2021/8/6 15:56
 * @Version 1.0
 */
public class MyThreadB301 extends Thread {
    private MyService301 myService301;

    public MyThreadB301(MyService301 myService301) {
        this.myService301 = myService301;
    }

    public MyThreadB301() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            myService301.get();
        }
    }
}
