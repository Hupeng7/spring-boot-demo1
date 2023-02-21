package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreExample
 * @Description
 * @Author H
 * @Date 2022/11/10 16:58
 * @Version 1.0
 */
@Slf4j
public class SemaphoreExample {
    private static final int threadCount = 200;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        //semaphore1(executorService, semaphore);
        //semaphore2(executorService, semaphore);
        semaphore3(executorService, semaphore);
        log.info("finish");
        executorService.shutdown();
    }

    /**
     * 每次获取、释放一个许可
     *
     * @param executorService
     * @param semaphore
     */
    private static void semaphore1(ExecutorService executorService, Semaphore semaphore) {
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 获取一个许可
                    test(threadNum);
                    semaphore.release(); // 释放一个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 每次获取、释放多个许可
     *
     * @param executorService
     * @param semaphore
     */
    private static void semaphore2(ExecutorService executorService, Semaphore semaphore) {
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3); // 获取多个许可
                    test(threadNum);
                    semaphore.release(3); // 释放多个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 每次获取、释放多个许可
     *
     * @param executorService
     * @param semaphore
     */
    private static void semaphore3(ExecutorService executorService, Semaphore semaphore) {
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    /**
                     * 尝试获取一个许可，也可以尝试获取多个许可，
                     * 支持尝试获取许可超时设置，超时后不再等待后续线程的执行
                     * 具体可以参见Semaphore的源码
                     *
                     */
                    if (semaphore.tryAcquire(2)) {
                        test(threadNum);
                        semaphore.release(); // 释放1个许可
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }

}
