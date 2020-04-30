package com.example.springbootdemomytool.utils.threadlocaldemo;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocalDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/23 15:34
 * @Version 1.0
 */
public class ThreadLocalDemo {

    /**
     * ThreadLocal变量，每个线程都有一个副本，互不干扰
     */
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        new ThreadLocalDemo().threadLocalTest();
    }

    private void threadLocalTest() throws Exception {
        THREAD_LOCAL.set("hello");
        String v = THREAD_LOCAL.get();
        System.out.println("Thread-0线程执行之前，" + Thread.currentThread().getName() + "线程取到的值: " + v);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String v = THREAD_LOCAL.get();
                System.out.println(Thread.currentThread().getName() + "线程取到的值: " + v);

                // 设置 ThreadLocal
                THREAD_LOCAL.set("world");
                v = THREAD_LOCAL.get();
                System.out.println("重新设置后，" + Thread.currentThread().getName() + "线程取到的值: " + v);
                System.out.println(Thread.currentThread().getName() + "线程执行结束");
            }
        }).start();
        // 等待所有线程执行结束
        TimeUnit.SECONDS.sleep(3);
        v = THREAD_LOCAL.get();
        System.out.println("Thread-0线程执行之后，" + Thread.currentThread().getName() + "线程取到的值: " + v);
    }


}
