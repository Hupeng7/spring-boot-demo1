package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NewFixedThreadPoolDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/3 14:21
 * @Version 1.0
 */
public class NewFixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(()->{
                System.out.println("Thread id is: "+ Thread.currentThread().getId());
                
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }




    }

}
