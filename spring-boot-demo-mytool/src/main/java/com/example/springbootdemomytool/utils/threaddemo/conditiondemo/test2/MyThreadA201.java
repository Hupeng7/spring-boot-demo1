package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test2;

/**
 * @ClassName MyThreadA201
 * @Description
 * @Author H
 * @Date 2021/8/6 15:29
 * @Version 1.0
 */
public class MyThreadA201 extends Thread {
    private MyService201 myService201;
    public MyThreadA201(MyService201 myService201){
        this.myService201 = myService201;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            myService201.set();
        }
    }
}
