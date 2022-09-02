package com.example.springbootdemomytool.utils.semaphoredemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.sleep;
import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.stop;

/**
 * @ClassName SemaphoreDemo
 * @Description
 * @Author hup
 * @Date 2020/9/15 17:18
 * @Version 1.0
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        semaphoreDemo.doThread();

    }

    private void doThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(6);

        Runnable longRunningTask = () -> {
            boolean permit = false;

            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired");
                    sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally { // 在异常情况中合理释放信号量十分重要
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 10)
                .forEach(i -> executorService.submit(longRunningTask));

        stop(executorService);

    }

}
