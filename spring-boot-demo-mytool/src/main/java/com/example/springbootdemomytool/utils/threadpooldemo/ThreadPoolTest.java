package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/3 14:35
 * @Version 1.0
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        /**
         * 首先，ThreadPoolExecutor自带了一些方法。
         * long getTaskCount()，获取已经执行或正在执行的任务数
         * long getCompletedTaskCount()，获取已经执行的任务数
         * int getLargestPoolSize()，获取线程池曾经创建过的最大线程数，根据这个参数，我们可以知道线程池是否满过
         * int getPoolSize()，获取线程池线程数
         * int getActiveCount()，获取活跃线程数（正在执行任务的线程数）
         * 其次，ThreadPoolExecutor留给我们自行处理的方法有3个，它在ThreadPoolExecutor中为空实现（也就是什么都不做）。
         * protected void beforeExecute(Thread t, Runnable r) // 任务执行前被调用
         * protected void afterExecute(Runnable r, Throwable t) // 任务执行后被调用
         * protected void terminated() // 线程池结束后被调用
         */
        ExecutorService executor = new ThreadPoolExecutor(2, 2, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1)) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("beforeExecute is called");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("afterExecute is called");
            }

            @Override
            protected void terminated() {
                System.out.println("terminated is called");
            }
        };

        executor.submit(() -> {
            System.out.println("this is a task");
        });

//        for (int i = 0; i < 10; i++) {
//            executor.submit(()->{
//                System.out.println("Thread id is: "+ Thread.currentThread().getId());
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }


        executor.shutdown();


    }


}
