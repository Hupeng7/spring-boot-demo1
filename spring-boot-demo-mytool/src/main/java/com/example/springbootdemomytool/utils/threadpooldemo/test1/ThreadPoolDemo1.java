package com.example.springbootdemomytool.utils.threadpooldemo.test1;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolDemo1
 * @Description 正确使用线程池 from：https://mp.weixin.qq.com/s/Qtq0I6_YPyjtW6SMM9-hqQ
 * @Author H
 * @Date 2021/2/22 15:56
 * @Version 1.0
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //fixedThreadPool();

        //cachedThreadPool();
        //singleThreadExecutor();
        //scheduledThreadPool();
        // workStealingPool();
        threadReject();
    }

    // 固定个数线程池
    public static void fixedThreadPool() {
        // 创建 2 个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，固定-线程：" + Thread.currentThread().getName());
            }
        };

        // 线程池执行任务 一次添加 4 个任务
        // 执行任务的方法有两种： submit 和 execute
        threadPool.submit(runnable);    // 执行方式 1 ： submit
        threadPool.execute(runnable);   // 执行方式 2 ： execute
        threadPool.execute(runnable);
        threadPool.execute(runnable);

        // 或者
        threadPool.execute(() -> {
            System.out.println("任务被执行1，固定-线程：" + Thread.currentThread().getName());
        });
    }

    // 缓存线程池
    public static void cachedThreadPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("任务被执行，缓存-线程：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            });
        }
    }

    // 单个线程数的线程池
    public static void singleThreadExecutor() {
        // 创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + ":任务被执行,线程：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
    }

    // 可执行延迟任务的线程池
    public static void scheduledThreadPool() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 添加定时任务 1s后执行
        System.out.println("添加任务，时间：" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行，时间：" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
        }, 1, TimeUnit.SECONDS);
    }

    // 单线程的可执行延迟任务的线程池
    public static void singleThreadScheduledExecutor() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        // 添加定时执行任务 2s后执行
        System.out.println("添加任务，时间：" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行，时间：" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }

        }, 2, TimeUnit.SECONDS);

    }

    // 抢占式线程池 任务执行顺序不确定 jdk8+
    public static void workStealingPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newWorkStealingPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行，线程名：" + Thread.currentThread().getName());
            });
        }
        // 确保任务执行完成
        while (!threadPool.isTerminated()) {
        }
    }

    /**
     * 最原始的创建线程池
     * 7 个参数代表的含义如下：
     * 参数 1：corePoolSize
     * <p>
     * 核心线程数，线程池中始终存活的线程数。
     * 参数 2：maximumPoolSize
     * <p>
     * 最大线程数，线程池中允许的最大线程数，当线程池的任务队列满了之后可以创建的最大线程数。
     * 参数 3：keepAliveTime
     * <p>
     * 最大线程数可以存活的时间，当线程中没有任务执行时，最大线程就会销毁一部分，最终保持核心线程数量的线程。
     * 参数 4：unit:
     * <p>
     * 单位是和参数 3 存活时间配合使用的，合在一起用于设定线程的存活时间 ，参数 keepAliveTime 的时间单位有以下 7 种可选：
     * TimeUnit.DAYS：天
     * TimeUnit.HOURS：小时
     * TimeUnit.MINUTES：分
     * TimeUnit.SECONDS：秒
     * TimeUnit.MILLISECONDS：毫秒
     * TimeUnit.MICROSECONDS：微妙
     * TimeUnit.NANOSECONDS：纳秒
     * 参数 5：workQueue
     * <p>
     * 一个阻塞队列，用来存储线程池等待执行的任务，均为线程安全，它包含以下 7 种类型：
     * ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
     * LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
     * SynchronousQueue：一个不存储元素的阻塞队列，即直接提交给线程不保持它们。
     * PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
     * DelayQueue：一个使用优先级队列实现的无界阻塞队列，只有在延迟期满时才能从中提取元素。
     * LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。与SynchronousQueue类似，还含有非阻塞方法。
     * LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
     * 较常用的是 LinkedBlockingQueue 和 Synchronous，线程池的排队策略与 BlockingQueue 有关。
     * 参数 6：threadFactory
     * <p>
     * 线程工厂，主要用来创建线程，默认为正常优先级、非守护线程。
     * 参数 7：handler
     * <p>
     * 拒绝策略，拒绝处理任务时的策略，系统提供了 4 种可选：
     * AbortPolicy：拒绝并抛出异常。
     * CallerRunsPolicy：使用当前调用的线程来执行此任务。
     * DiscardOldestPolicy：抛弃队列头部（最旧）的一个任务，并执行当前任务。
     * DiscardPolicy：忽略并抛弃当前任务。
     * 默认策略为 AbortPolicy。
     */
    public static void myThreadPoolExecutor() {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,
                10,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()
        );
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行，线程名：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            });
        }
    }

    // 线程拒绝策略
    public static void threadReject() {
        // 任务的具体方法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务被执行，执行时间：" + new Date() + " 执行线程：" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建线程 线程的任务队列长度为1
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                2,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        // 添加并执行 4 个任务
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);

    }


}
