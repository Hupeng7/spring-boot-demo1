package com.example.springbootdemomytool.utils.threadpooldemo;

import com.example.springbootdemomytool.utils.testdemo.DateUtil;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolTest3
 * @Description
 *  url:https://www.jianshu.com/p/aeb391e4edb0
 * @Author H
 * @Date 2021/1/7 19:08
 * @Version 1.0
 */
public class ScheduledThreadPoolTest3 {

    public static void main(String[] args) throws Exception {
        // testSchedule4Runnable();

        // testSchedule4Callable();

        //testScheduleAtFixRate();

        testScheduleWithFixedDelay();
    }

    /**
     * 延迟执行时间为1秒，任务执行3秒，任务只执行一次，同时通过Future.get()阻塞直至任务执行完毕。
     *
     * @throws Exception
     */
    public static void testSchedule4Runnable() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture future = scheduledExecutorService.schedule(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + DateUtil.getDefaultFormat(new Date()));
        }, 1000, TimeUnit.MILLISECONDS);
        System.out.println("schedule finish time: " + DateUtil.getDefaultFormat(new Date()));

        System.out.println("Runnable future's result is: " + future.get() +
                ", and time is: " + DateUtil.getDefaultFormat(new Date()));
    }

    /**
     * 运行看到的结果和Runnable基本相同，唯一的区别在于future.get()能拿到Callable返回的真实结果。
     *
     * @throws Exception
     */
    public static void testSchedule4Callable() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<String> future = scheduledExecutorService.schedule(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + DateUtil.getDefaultFormat(new Date()));
            return "success";
        }, 1000, TimeUnit.MILLISECONDS);
        System.out.println("schedule finish time: " + DateUtil.getDefaultFormat(new Date()));

        System.out.println("Callable future's result is: " + future.get() +
                " , and time is: " + DateUtil.getDefaultFormat(new Date()));
    }

    /**
     * 任务初始延迟1秒，任务执行3秒，任务执行间隔为1秒。
     */
    public static void testScheduleAtFixRate() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + DateUtil.getDefaultFormat(new Date()));
        }, 1000L, 1000L, TimeUnit.MILLISECONDS);
        System.out.println("schedule finish time: " + DateUtil.getDefaultFormat(new Date()));
        while (true) {

        }
    }

    /**
     * 任务初始延迟1秒，任务执行3秒，任务执行间隔为1秒
     */
    public static void testScheduleWithFixedDelay() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + DateUtil.getDefaultFormat(new Date()));
        }, 1000L, 1000L, TimeUnit.MILLISECONDS);
        System.out.println("schedule finish time: " + DateUtil.getDefaultFormat(new Date()));
        while (true) {
        }
    }


}
