package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test3;

/**
 * @ClassName Run301
 * @Description
 * @Author H
 * @Date 2021/8/6 15:59
 * @Version 1.0
 */
public class Run301 {

    public static void main(String[] args) {
        MyService301 myService301 = new MyService301();
        MyThreadA301[] myThreadA301 = new MyThreadA301[10];
        MyThreadB301[] myThreadB301 = new MyThreadB301[10];
        for (int i = 0; i < 5; i++) {
            System.out.println("执行 ---> " + i);
            myThreadA301[i] = new MyThreadA301(myService301);
            myThreadB301[i] = new MyThreadB301(myService301);

            myThreadA301[i].start();
            myThreadB301[i].start();
        }
    }
}
