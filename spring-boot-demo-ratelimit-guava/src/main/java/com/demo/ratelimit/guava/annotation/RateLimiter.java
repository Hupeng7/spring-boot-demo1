package com.demo.ratelimit.guava.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Leo
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    int NOT_LIMITED = 0;

    /**
     * qps
     *
     * @return
     */
    @AliasFor("qps") double value() default NOT_LIMITED;

    /**
     * value
     *
     * @return
     */
    @AliasFor("value") double qps() default NOT_LIMITED;

    /**
     * 超时时长
     *
     * @return
     */
    int timeout() default 0;

    /**
     * 超时时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}

