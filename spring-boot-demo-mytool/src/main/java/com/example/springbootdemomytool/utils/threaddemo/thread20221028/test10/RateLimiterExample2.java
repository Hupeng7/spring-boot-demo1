package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test10;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RateLimiterExample2
 * @Description
 * @Author H
 * @Date 2022/11/22 17:59
 * @Version 1.0
 */
@Slf4j
public class RateLimiterExample2 {
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) {
        for (int index = 0; index < 100; index++) {
            rateLimiter.acquire();
            handle(index);
        }
    }

    private static void handle(int i) {
        log.info("do {}", i);
    }

}
