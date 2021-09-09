package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test2;

/**
 * @ClassName MyThreadB201
 * @Description
 * @Author H
 * @Date 2021/8/6 15:32
 * @Version 1.0
 */
public class MyThreadB201 extends Thread {
    private MyService201 myService201;

    public MyThreadB201(MyService201 myService201) {
        this.myService201 = myService201;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            myService201.get();
        }
    }
}
