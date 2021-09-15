package com.demo.encryptionanddecryption.annotation;


import java.lang.annotation.*;

import static com.demo.encryptionanddecryption.enums.EncryptConstant.ENCRYPT;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptMethod {

    String type() default ENCRYPT;
}
