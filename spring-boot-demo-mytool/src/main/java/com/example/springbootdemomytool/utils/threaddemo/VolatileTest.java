package com.example.springbootdemomytool.utils.threaddemo;

/**
 * @ClassName VolatileTest
 * @Description https://mp.weixin.qq.com/s/tZy-SJeMqmLuGLJCmuyXkQ
 * @Author H
 * @Date 2021/3/25 15:15
 * @Version 1.0
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        System.out.println("Thread.activeCount() = " + Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();

        // test1();
        findAllThread();


    }

    private static Thread[] findAllThread() {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        while (currentGroup.getParent() != null) {
            // 返回此线程组的父线程组
            currentGroup = currentGroup.getParent();
        }
        // 此线程组中活动线程的估计数
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        // 把对此线程组中的所有活动子组的引用复制到指定数组中
        currentGroup.enumerate(lstThreads);

        for (Thread thread : lstThreads) {
            System.out.println("线程数量：" + noThreads + " " +
                    "线程id: " + thread.getId() +
                    " 线程名称：" + thread.getName() +
                    " 线程状态： " + thread.getState()
            );
        }

        return lstThreads;
    }

    private static void test1() {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            }).start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }

}
