package com.demo.encryptionanddecryption.annotation;

import java.lang.annotation.*;

/**
 * @ClassName EncryptField
 * @Description
 * @Author H
 * @Date 2021/9/15 10:45
 * @Version 1.0
 */

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {
    String[] value() default "";
}
