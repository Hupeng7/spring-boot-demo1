package com.demo.ratelimit.redis.handler;

import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常拦截
 * @Author Leo
 * @Date 2020/1/9 16:58
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Dict handler(RuntimeException ex) {
        return Dict.create().set("msg", ex.getMessage());
    }
}
