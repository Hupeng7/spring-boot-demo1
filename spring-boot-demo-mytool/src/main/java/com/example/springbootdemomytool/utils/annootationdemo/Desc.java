package com.example.springbootdemomytool.utils.annootationdemo;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited // 表示该注解会被自动继承
@Documented
public @interface Desc {
    enum Color {
        White, Grayish, Yellow;
    }

    Color c() default Color.White;

}
