package com.example.springbootdemomytool.utils.multitaskdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @ClassName MultiTaskDemo
 * @Description 多任务
 * @Author Leo
 * @Date 2020/5/14 18:13
 * @Version 1.0
 */
public class MultiTaskDemo {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList(1000);
        for (int i = 0; i < 1000; i++) {
            list.add("item" + i);
        }
        if (list.size() > 0) {
            //4个线程同时执行插入
            System.out.println("start bath save coupon...");
            int size = list.size() / 4;
            Future<Boolean> task1 = TaskAsyncHandler.saveCouponListAsync("task1", list.subList(0, size));
            Future<Boolean> task2 = TaskAsyncHandler.saveCouponListAsync("task2", list.subList(size, 2 * size));
            Future<Boolean> task3 = TaskAsyncHandler.saveCouponListAsync("task3", list.subList(2 * size, 3 * size));
            Future<Boolean> task4 = TaskAsyncHandler.saveCouponListAsync("task4", list.subList(3 * size, list.size()));
            while (true) {
                if (task1.isDone() && task2.isDone() && task3.isDone() && task4.isDone()) {
                    System.out.println("是都成功");
                    //通过返回值判断异步任务是否异常
                    System.out.println("1" + task1.get() + "2" + task2.get() + "3" + task3.get() + "4" + task4.get());
                    if (task1.get() && task2.get() && task3.get() && task4.get()) {
                    } else {
                        throw new Exception("coupon text insert error");
                    }
                    break;
                } else {
                    System.out.println("不是都成功");
                }
            }

        }
    }


}
