package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ScheduledThreadPoolExecutorTest
 * @Description
 * @Author H
 * @Date 2022/11/8 15:17
 * @Version 1.0
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试ScheduledThreadPoolExecutor");
            }
        }, 1, 1, TimeUnit.SECONDS);

        // 主线程休眠10秒
        Thread.sleep(10000);

        System.out.println("close thread pool now...");
        // 关闭线程池
        scheduledExecutorService.shutdown();
        boolean isClosed;
        // 等待线程池终止
        do {
            isClosed = scheduledExecutorService.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println("正在等待线程池中的任务执行完成");
        } while (!isClosed);
        System.out.println("所有线程执行结束，线程池关闭");
    }
}
