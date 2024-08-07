package com.example.springbootdemomytool.utils.threadpooldemo.test2;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 同步器CountDownLatch是基于AQS(AbastractQueuedSynchronizer)抽象队列同步qi实现
 */
public class ParallelFetcher {
    private final long timeout;
    private final CountDownLatch latch;
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(16, 32, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(400));

    public ParallelFetcher(int jobSize, long timeoutMill) {
        latch = new CountDownLatch(jobSize);
        timeout = timeoutMill;
    }

    public void submitJob(Runnable runnable) {
        EXECUTOR.execute(() -> {
            runnable.run();
            latch.countDown();
        });
    }

    public void await() {
        try {
            this.latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }
    }

    public void dispose() {
        EXECUTOR.shutdown();
    }

    public static void main(String[] args) {
        final String userid = "param";
        final CountLogic mock = new CountLogic();
        ParallelFetcher fetcher = new ParallelFetcher(3, 3000);

        long start = System.currentTimeMillis();
        ConcurrentHashMap<String, Integer> result = new ConcurrentHashMap<>(4);
        fetcher.submitJob(() -> result.put("method0", mock.method0(userid)));
        fetcher.submitJob(() -> result.put("method1", mock.method1(userid)));
        fetcher.submitJob(() -> result.put("method2", mock.method2(userid)));
        fetcher.await();
        System.out.println("================= DONE ===================, result = " + result + ", time = " + (System.currentTimeMillis() - start));

        // 如果是正式环境，此处需要注释掉，否则关闭连接池，第二次请求报错
        fetcher.dispose();

        int total = 0;
        for (Integer var : result.values()) {
            total = total + var;
        }
        System.out.println("================= total = " + total);
    }

}

