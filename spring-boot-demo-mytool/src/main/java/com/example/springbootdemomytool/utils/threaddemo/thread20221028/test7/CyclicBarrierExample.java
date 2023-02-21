package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName CyclicBarrierExample
 * @Description
 * @Author H
 * @Date 2022/11/10 17:35
 * @Version 1.0
 */
@Slf4j
public class CyclicBarrierExample {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    // 在声明CyclicBarrier的时候，还可以指定一个Runnable，当线程达到屏障的时候，可以优先执行Runnable中的方法。
    //示例代码如下
    private static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(5, () -> {
        log.info("callback is running");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    //race(threadNum);
                    //race1(threadNum);
                    race2(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} continue", threadNum);
    }

    /**
     * 等待超时
     *
     * @param threadNum
     * @throws Exception
     */
    private static void race1(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException e) {
            log.warn("error: ", e);
        }
        log.info("{} continue", threadNum);
    }

    private static void race2(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier1.await();
        log.info("{} continue", threadNum);
    }

}
