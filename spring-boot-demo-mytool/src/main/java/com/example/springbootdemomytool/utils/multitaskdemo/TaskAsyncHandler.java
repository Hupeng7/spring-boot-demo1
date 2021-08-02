package com.example.springbootdemomytool.utils.multitaskdemo;

import org.springframework.scheduling.annotation.AsyncResult;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @ClassName TaskAsyncHandler
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/14 18:18
 * @Version 1.0
 */
public class TaskAsyncHandler {
    public static Future<Boolean> saveCouponListAsync(String taskName, List<String> list) {
        if ("task3".equalsIgnoreCase(taskName)) {
            System.out.println("错误的task：" + taskName);
            //throw new Exception("exception");
            return new AsyncResult<>(false);
        }
        for (String item : list) {
            System.out.println(taskName + "write item: " + item);
        }
        return new AsyncResult<>(true);
    }
}
