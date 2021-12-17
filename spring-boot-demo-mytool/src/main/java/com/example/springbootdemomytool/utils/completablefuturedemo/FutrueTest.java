package com.example.springbootdemomytool.utils.completablefuturedemo;

import com.example.springbootdemomytool.utils.httpclientdemo.demo2.JsonUtils;

import java.util.concurrent.*;

/**
 * @ClassName FutrueTest
 * @Description
 * @Author H
 * @Date 2021/12/15 11:59
 * @Version 1.0
 */
public class FutrueTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        //futureTest();
        //completableFutureTest();
        // completableFutureTest1();
        // futureThenRunTest2();
        // futureThenAcceptTest();
        // futureThenApplyTest();
        // futureExceptionTest();
        futureWhenTest();
    }

    public static void completableFutureTest() throws InterruptedException, ExecutionException, TimeoutException {
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 1L;
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户基本信息
        CompletableFuture<UserInfo> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return userInfoService.getUserInfo(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        Thread.sleep(300);

        CompletableFuture<MedalInfo> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                return medalService.getMedalInfo(userId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        UserInfo userInfo = completableFuture.get(2, TimeUnit.SECONDS);
        MedalInfo medalInfo = completableFuture1.get();
        System.out.println("总共用时:" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void futureTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 111L;
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户的基本信息
        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(new Callable<UserInfo>() {
            @Override
            public UserInfo call() throws Exception {
                return userInfoService.getUserInfo(userId);
            }
        });
        executorService.submit(userInfoFutureTask);

        Thread.sleep(300);

        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(new Callable<MedalInfo>() {
            @Override
            public MedalInfo call() throws Exception {
                return medalService.getMedalInfo(userId);
            }
        });
        executorService.submit(medalInfoFutureTask);

        UserInfo userInfo = userInfoFutureTask.get();
        MedalInfo medalInfo = medalInfoFutureTask.get();

        System.out.println("总共用时 " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void completableFutureTest1() {
        // 可以自定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // runAsync的使用
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> System.out.println("run,1111"), executorService);

        // supplyAsync 的使用
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supply ,");
            return "完成 ";
        }, executorService);

        // runAsync 的future没有返回值，输出null
        System.out.println(runFuture.join());
        // supplyAsync的future 有返回值
        System.out.println(supplyFuture.join());
        // 线程池需要关闭
        executorService.shutdown();
    }

    public static void futureThenRunTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("先执行第一个CompletableFuture方法任务");
            return "bruce";
        });

        CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
            System.out.println("接着执行第二个任务");
        });

        System.out.println(thenRunFuture.get());
    }

    public static void futureThenAcceptTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("原始CompletableFuture方法任务");
            return "Bruce";
        });

        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
            if ("Bruce".equals(a)) {
                System.out.println("关注了");
            }
            System.out.println("先考虑考虑");
        });
        System.out.println(thenAcceptFuture.get());
    }

    public static void futureThenApplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("原始CompletableFuture方法任务");
            return "Bruce";
        });

        CompletableFuture<String> thenApplyFuture = orgFuture.thenApply((a) -> {
            if ("Bruce".equals(a)) {
                return "关注了";
            }
            return "先考虑考虑";
        });
        System.out.println(thenApplyFuture.get());
    }

    public static void futureExceptionTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称: " + Thread.currentThread().getName());
            throw new RuntimeException();
        });

        CompletableFuture<String> exceptionFuture = orgFuture.exceptionally((e) -> {
            e.printStackTrace();
            return "你的程序异常啦";
        });
        System.out.println(exceptionFuture.get());
    }

    public static void futureWhenTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("当前线程名称: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Bruce";
                });

        CompletableFuture<String> rstFuture = orgFuture.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦,还把" + a + "传过来");
            if ("Bruce".equals(a)) {
                System.out.println("666");
            }
            System.out.println("233333");
        });
        System.out.println(rstFuture.get());
    }

    public static void futureHandlerTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程名称："+Thread.currentThread().getName());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Bruce";
        });

        CompletableFuture<String> rstFuture = orgFuture.handle((a,throwable)->{
            System.out.println("上个任务执行完了，还把"+a+"传过来");
            if ("Bruce".equals(a)) {
                System.out.println("666");
                return "关注了";
            }
            System.out.println("2333333333");
            return null;
        });
        System.out.println(rstFuture.get());
    }

}
