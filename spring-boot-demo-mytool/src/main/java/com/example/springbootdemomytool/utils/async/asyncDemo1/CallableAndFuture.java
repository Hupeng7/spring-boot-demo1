package com.example.springbootdemomytool.utils.async.asyncDemo1;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName CallableAndFuture
 * @Description
 * @Author H
 * @Date 2022/7/29 14:16
 * @Version 1.0
 */
public class CallableAndFuture {

    public static ExecutorService executorService = new ThreadPoolExecutor(
            4, 40,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<Runnable>(1024),
            new ThreadFactoryBuilder().setNameFormat("").build(),
            new ThreadPoolExecutor.AbortPolicy());

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "异步处理， Callable 返回结果";
        }
    }

    public static void main(String[] args) {
        Future<String> future = executorService.submit(new MyCallable());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
