package com.example.springbootdemomytool.utils.demo.lockdemo;

/**
 * @ClassName TaskWithSync
 * @Description
 * @Author hup
 * @Date 2020/8/20 19:09
 * @Version 1.0
 */
public class TaskWithSync extends Task implements Runnable {
    @Override
    public void run() {
        // 内部锁
        synchronized ("A") {
            doSomething();
        }
    }
}
