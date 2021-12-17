package com.example.springbootdemomytool.utils.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VolatileSolveAtomDemo
 * @Description
 * @Author H
 * @Date 2021/12/15 18:10
 * @Version 1.0
 */
public class VolatileSolveAtomDemo {
    // 原子Integer类型 保证原子性
    private AtomicInteger atomicNumber = new AtomicInteger();

    // 底层通过CAS保证原子性
    public void addPlusPlus() {
        atomicNumber.getAndIncrement();
    }

    public static void main(String[] args) {
        VolatileSolveAtomDemo volatileSolveAtomDemo = new VolatileSolveAtomDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    volatileSolveAtomDemo.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 后台默认两个线程：一个是main线程，一个是gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // 因为volatile不保证原子性，所以选择原子类AtomicInteger来解决volatile不保证原子性问题
        // 最终每次程序执行结果都等于20000
        System.out.println(Thread.currentThread().getName() + "\tfinal number result = " + volatileSolveAtomDemo.atomicNumber.get());
    }

}
