package com.example.springbootdemomytool.utils.demo.lockdemo;

/**
 * @ClassName Foo
 * @Description
 * @Author hup
 * @Date 2020/8/21 10:32
 * @Version 1.0
 */
public class Foo implements Runnable {
    @Override
    public void run() {
        fun(10);
    }

    // 递归函数
    public synchronized void fun(int i) {
        if (--i > 0) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println(i);
            fun(i);
        }

    }
}
