package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test11;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @ClassName TokenBucketLimiter
 * @Description
 * @Author H
 * @Date 2022/11/28 18:04
 * @Version 1.0
 */
public class TokenBucketLimiter {

    public static void main(String[] args) {
        // 每秒钟生产5个令牌
        RateLimiter limiter = RateLimiter.create(5);
        //test1(limiter);
        test2(limiter);

        String a;

    }

    private static void test1(RateLimiter limiter) {
        // 返回值表示从令牌桶中获取一个令牌所花费的时间  单位是秒
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
    }

    private static void test2(RateLimiter limiter) {
        // 返回值表示从令牌桶中获取一个令牌所花费的时间  单位是秒
        System.out.println(limiter.acquire(50));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
    }
}
