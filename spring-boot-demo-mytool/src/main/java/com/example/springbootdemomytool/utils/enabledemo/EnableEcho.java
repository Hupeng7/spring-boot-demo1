package com.example.springbootdemomytool.utils.enabledemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BamuImportBeanDefinitionRegistrar.class)
public @interface EnableEcho {
    // 传入报名
    String[] packages() default "";
}
