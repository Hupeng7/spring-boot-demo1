package com.demo.ratelimit.redis.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RateLimiter
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/9 15:57
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    long DEFAULT_REQUEST = 10;

    /**
     * max 最大请求数
     *
     * @return
     */
    @AliasFor("max") long value() default DEFAULT_REQUEST;

    /**
     * max 最大请求数
     *
     * @return
     */
    @AliasFor("value") long max() default DEFAULT_REQUEST;

    /**
     * 限流key
     *
     * @return
     */
    String key() default "";

    /**
     * 超时时长，默认1分钟
     *
     * @return
     */
    long timeout() default 1;

    /**
     * 超时时间单位，默认 分钟
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;

}
