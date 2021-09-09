package com.example.springbootdemomytool.utils.threaddemo.conditiondemo.test1;

/**
 * @ClassName Run
 * @Description
 * @Author H
 * @Date 2021/8/6 11:34
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        // 错误的例子
//        ThreadA a = new ThreadA(myService);
//        a.start();

        // 正确的例子
        ThreadB b = new ThreadB(myService);
        b.start();
    }
}
