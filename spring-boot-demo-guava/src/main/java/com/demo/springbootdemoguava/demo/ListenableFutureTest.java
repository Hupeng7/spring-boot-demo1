package com.demo.springbootdemoguava.demo;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ListenableFutureTest
 * @Description ListenableFuture多了一个回调的api支持,Futures封装了更多的支持比如合并一组ListenableFuture等功能,比较实用.
 * @Author hup
 * @Date 2020/7/16 17:03
 * @Version 1.0
 */
@Slf4j
public class ListenableFutureTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("ListeningExecutorService.Pool-" + atomicInteger.getAndIncrement());
                return t;
            }
        });
        // 包装得到ListeningExecutorService
        ListeningExecutorService les = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<String> listenableFuture = les.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "aaa";
        });

        // 普通的使用监听
        listenableFuture.addListener(() -> {
            try {
                log.info("listenableFuture.addListener...success : " + listenableFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, executorService);

        // 通过Futures工具类使用监听
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                log.info("Futrues.addCallback success..." + result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, executorService);

        log.info("-------------------------------------------------");
        log.info("正常处理返回值 : " + listenableFuture.get());
        log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        // 对ListenableFuture进行合并操作，
        List<ListenableFuture<String>> listenableFutures = new ArrayList<>();
        listenableFutures.add(les.submit(() -> "aaa"));
        listenableFutures.add(les.submit(() -> "bbb"));
        listenableFutures.add(les.submit(() -> {
            throw new RuntimeException("runtime exception");
        }));
        listenableFutures.add(les.submit(() -> "ccc"));

        // successfulAsList 有异常则返回null
        // allAsList 有一个异常都会走failure
        ListenableFuture<List<String>> listListenableFuture = Futures.successfulAsList(listenableFutures);
        // ListenableFuture<List<String>> listListenableFuture1 = Futures.allAsList(listenableFutures);
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                log.info("Futures.successfulAsList result: " + result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, executorService);

        log.info("listListenableFuture.get():" + listenableFuture.get());
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }
}
