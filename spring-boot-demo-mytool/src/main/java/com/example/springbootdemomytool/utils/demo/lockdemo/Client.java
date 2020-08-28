package com.example.springbootdemomytool.utils.demo.lockdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Client
 * @Description
 * 【显式锁】是同时运行的，显然在pool-1-thread-1线程执行到sleep时，其他两个线程也会运行到这里，
 * 一起等待，让后一起输出
 * 【内部锁】符合预期，pool-2-thread-1线程在运行时其他线程处于等待状态，pool-2-thread-1执行完毕后，
 * JVM从等待线程池中随机获得一个线程执行，再执行其他等待线程
 *
 * 为什么显式锁Lock不出现互斥情况？
 * 因为对于同步资源来说，显式锁是对象级别的锁，而内部锁是类级别的锁，也就是说Lock锁是跟随对象的，
 * synchronized锁是跟随类的，更简单地说把Lock定义为多线程类的私有属性是起不到资源互斥作用的，
 * 除非是把Lock定义为所有线程的共享变量。
 * @Author hup
 * @Date 2020/8/20 19:10
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) throws Exception {
        runTasks(TaskWithLock.class);  // 显式锁
        runTasks(TaskWithSync.class);  // 内部锁
    }

    public static void runTasks(Class<? extends Runnable> clz) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println("开始执行" + clz.getSimpleName() + "任务");
        for (int i = 0; i < 3; i++) {
            service.submit(clz.newInstance());
        }
        // 等待足够长的时间，然后关闭执行器
        TimeUnit.SECONDS.sleep(5);
        System.out.println("=======" + clz.getSimpleName() + "任务执行完毕=======");
        //
        service.shutdown();
    }
}
