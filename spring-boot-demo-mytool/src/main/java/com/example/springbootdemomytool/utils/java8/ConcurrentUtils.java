package com.example.springbootdemomytool.utils.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ConcurrentUtils
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/15 15:37
 * @Version 1.0
 */
public class ConcurrentUtils {

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }


}
