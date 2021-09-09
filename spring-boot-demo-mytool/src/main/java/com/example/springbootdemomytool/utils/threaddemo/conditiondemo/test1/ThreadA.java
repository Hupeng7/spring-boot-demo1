package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName ThreadA
 * @Description
 * @Author H
 * @Date 2021/8/6 11:32
 * @Version 1.0
 */
public class ThreadA extends Thread {
    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.awaitIsWrong();
    }
}
