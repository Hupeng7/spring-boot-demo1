package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NewCachedThreadPooldemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/3 14:08
 * @Version 1.0
 */
public class NewCachedThreadPoolDemo {


    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }


    }


}
