package com.example.springbootdemomytool.utils.async.asyncDemo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName TheadPool1
 * @Description
 * @Author H
 * @Date 2022/7/29 14:32
 * @Version 1.0
 */
public class TheadPool1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("子线程开始计算: ");
            Integer sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        });

        // 线程池执行任务，运行结果再 futureTask 对象里面
        executorService.submit(futureTask);

        try {
            System.out.println("task运行结果计算的总和为: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
