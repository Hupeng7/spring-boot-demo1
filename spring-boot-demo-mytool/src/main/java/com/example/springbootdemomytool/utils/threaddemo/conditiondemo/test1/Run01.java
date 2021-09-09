package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName Run01
 * @Description
 * @Author H
 * @Date 2021/8/6 13:37
 * @Version 1.0
 */
public class Run01 {
    public static void main(String[] args) throws InterruptedException {
        MyService01 myService01 = new MyService01();
        ThreadA01 threadA01 = new ThreadA01(myService01);
        threadA01.start();
        Thread.sleep(3000);
        myService01.signal();
    }
}
