package com.example.springbootdemomytool.utils.aspectdemo.aspect;

import java.lang.annotation.*;

/**
 * @author leo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
