package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test3;

/**
 * @ClassName MyThreadA301
 * @Description
 * @Author H
 * @Date 2021/8/6 15:54
 * @Version 1.0
 */
public class MyThreadA301 extends Thread {
    private MyService301 myService301;

    public MyThreadA301(MyService301 myService301) {
        this.myService301 = myService301;
    }

    public MyThreadA301() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            myService301.set();
        }
    }
}
