package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test10;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RateLimiterExample
 * @Description
 * @Author H
 * @Date 2022/11/22 17:52
 * @Version 1.0
 */
@Slf4j
public class RateLimiterExample {
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) {
        for (int index = 0; index < 100; index++) {
            if (rateLimiter.tryAcquire(190, TimeUnit.MILLISECONDS)) {
                handle(index);
            }
        }
    }

    private static void handle(int i) {
        log.info("do {}", i);
    }


}
