package com.demo.encryptionanddecryption.aspect;

import com.demo.encryptionanddecryption.annotation.EncryptField;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.demo.encryptionanddecryption.enums.EncryptConstant.DECRYPT;
import static com.demo.encryptionanddecryption.enums.EncryptConstant.ENCRYPT;

/**
 * @ClassName EncryptHandler
 * @Description
 * @Author H
 * @Date 2021/9/15 11:05
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class EncryptHandler {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Pointcut("@annotation(com.demo.encryptionanddecryption.annotation.EncryptMethod)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        /**
         * 加密
         */
        encrypt(proceedingJoinPoint);

        /**
         * 解密
         */
        Object decrypt = decrypt(proceedingJoinPoint);
        return decrypt;

    }

    private Object decrypt(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            Object obj = proceedingJoinPoint.proceed();
            if (obj == null) {
                return result;
            }
            if (obj instanceof String) {
                decryptValue(obj);
            } else {
                result = handler(obj, DECRYPT);
            }
            // TODO 其余类型自己看实际情况加
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    private void encrypt(ProceedingJoinPoint proceedingJoinPoint) {
        try {

            Object[] objects = proceedingJoinPoint.getArgs();
            if (objects.length == 0) {
                return;
            }
            for (Object o : objects) {
                if (o instanceof String) {
                    encryptValue(o);
                } else {
                    handler(o, ENCRYPT);
                }
                // TODO 其余类型自己看实际情况加
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object handler(Object object, String type) throws IllegalAccessException {
        if (Objects.isNull(object)) {
            return null;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSucureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSucureField) {
                field.setAccessible(true);
                String realValue = (String) field.get(object);
                String value;
                if (DECRYPT.equals(type)) {
                    value = stringEncryptor.decrypt(realValue);
                } else {
                    value = stringEncryptor.encrypt(realValue);
                }
                field.set(object, value);
            }
        }
        return object;
    }

    public String encryptValue(Object realValue) {
        String value = null;
        try {
            value = stringEncryptor.encrypt(String.valueOf(realValue));
        } catch (Exception e) {
            return value;
        }
        return value;
    }

    public String decryptValue(Object realValue) {
        String value = String.valueOf(realValue);
        try {
            value = stringEncryptor.decrypt(value);
        } catch (Exception e) {
            return value;
        }
        return value;
    }


}
