package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ThreadPoolTest3
 * @Description 1、CountDownLatch: 一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行
 * 2、ThreadPoolExecutor/ExecutorService : 线程池，使用线程池可以复用线程，降低频繁创建线程造成的性能消耗，同时对线程的创建、启动、停止、销毁等操作更简便。
 * 3、使用场景举例:
 * 年末公司组织团建，要求每一位员工周六上午8点到公司门口集合，统一乘坐公司所租大巴前往目的地.
 * 在这个案例中，公司作为主线程，员工作为子线程。
 * @Author H
 * @Date 2024/7/30 18:39
 * @Version 1.0
 */
public class ThreadPoolTest3 {


    public static void main(String[] args) throws Exception {
//        for (int i = 1; i <= 3; i++) {
//            System.out.println("第 " + i + " 次执行任务 start ");
//            boolean b = multiTask();
//            System.out.println("第 " + i + " 次执行任务  end." + b);
//        }
        Map<String, String> aa = new HashMap<>();
        ConcurrentHashMap<String, String> aMap = new ConcurrentHashMap<>();
        aMap.put("name", "");
        aMap.put("mobile", "");
        aMap.put("idNo", "");

        // 将ConcurrentHashMap转为HashMap，下面aa = aMap直接赋值不可传递，可以一个一个put,或者putAll()
        aa.putAll(aMap);

        //  aa = aMap;
        System.out.println(aa.keySet());

//        Boolean b = aMap.size() == 3;
//        System.out.println(b);
    }

    private static boolean multiTask() {
        // employee number
        int count = 3;

        CountDownLatch countDownLatch = new CountDownLatch(count);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                8,
                16,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3)
        );

        System.out.println("company send a massage,everybody go together at 8:00");

//        for (int i = 0; i < count; i++) {
//            Thread.sleep(10);
//            threadPoolExecutor.submit(new Employee(countDownLatch, i + 1));
//        }

        String e1 = "", e2 = "", e3 = "";
        try {
            Future<String> future1, future2, future3;

            future1 = threadPoolExecutor.submit(new Employee(countDownLatch, 1));
            future2 = threadPoolExecutor.submit(new Employee(countDownLatch, 2));
            future3 = threadPoolExecutor.submit(new Employee(countDownLatch, 3));

            try {
                e1 = future1.get(3000, TimeUnit.MILLISECONDS);
                e2 = future2.get(3000, TimeUnit.MILLISECONDS);
                e3 = future3.get(3000, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println("future1.get() error");
                return true;
            }

            try {
                countDownLatch.await();

                System.out.println("done done done!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("countDownLatch.await() error");
                return true;
            } finally {
                threadPoolExecutor.shutdown();
            }

            System.out.println("e1:" + e1 + ",e2:" + e2 + ",e3:" + e3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("all error");
            System.out.println("e1:" + e1 + ",e2:" + e2 + ",e3:" + e3);
            return true;
        }

        return false;
    }

}

