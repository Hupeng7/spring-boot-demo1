package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SimpleDateFormatTest01
 * @Description  在高并发下使用SimpleDateFormat类格式化日期时抛出了异常，SimpleDateFormat类不是线程安全的！！！
 * @Author H
 * @Date 2022/10/31 19:02
 * @Version 1.0
 */
public class SimpleDateFormatTest01 {
    // 执行总次数
    private static final int EXECUTE_COUNT = 1000;
    // 同时运行的线程数量
    private static final int THREAD_COUNT = 20;
    // SimpleDateFormat对象
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        // 1. 局部变量法
                        //SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        //simpleDateFormat1.parse("2022-11-01");

                        // 2. 锁
//                        synchronized (simpleDateFormat) {
//                            simpleDateFormat.parse("2022-11-01");
//                        }

                        simpleDateFormat.parse("2022-11-01");
                    } catch (ParseException e) {
                        System.out.println("线程: " + Thread.currentThread().getName() + "格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    } catch (NumberFormatException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + "格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");

    }
}
