package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test2;

/**
 * @ClassName Run201
 * @Description 交替执行
 * @Author H
 * @Date 2021/8/6 15:33
 * @Version 1.0
 */
public class Run201 {
    public static void main(String[] args) {
        MyService201 myService201 = new MyService201();
        MyThreadA201 myThreadA201 = new MyThreadA201(myService201);
        myThreadA201.setName("A201");
        myThreadA201.start();

        MyThreadB201 myThreadB201 = new MyThreadB201(myService201);
        myThreadB201.setName("B201");
        myThreadB201.start();
    }

}
