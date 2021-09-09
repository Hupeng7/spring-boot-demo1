package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName ThreadB
 * @Description
 * @Author H
 * @Date 2021/8/6 11:43
 * @Version 1.0
 */
public class ThreadB extends Thread {
    private MyService myService;

    public ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.awaitIsCorrect();
    }
}
