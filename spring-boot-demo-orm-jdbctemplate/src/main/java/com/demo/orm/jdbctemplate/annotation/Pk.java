package com.demo.orm.jdbctemplate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Pk
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/27 16:39
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Pk {
    /**
     * 自增
     *
     * @return 自增主键
     */
    boolean auto() default true;
}
