package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test9;

/**
 * @ClassName ThreadLocalTest3
 * @Description 使用InheritableThreadLocal类存储本地变量时，子线程能够获取到父线程中设置的本地变量。
 * @Author H
 * @Date 2022/11/14 16:38
 * @Version 1.0
 */
public class ThreadLocalTest3 {
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 在主线程中设置值
        threadLocal.set("ThreadLocalTest");
        // 在子线程中获取值
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程获取值：" + threadLocal.get());
            }
        });

        // 启动子线程
        thread.start();
        // 在主线程中获取值
        System.out.println("主线程获取值：" + threadLocal.get());
    }
}
