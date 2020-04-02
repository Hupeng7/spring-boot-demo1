package com.example.springbootdemomytool.utils.restfuldemo.utils;

import com.example.springbootdemomytool.utils.restfuldemo.exceptions.CheckException;
import org.springframework.context.MessageSource;

/**
 * @ClassName CheckUtil
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/1 15:47
 * @Version 1.0
 */
public class CheckUtil {
    private static MessageSource resources;

    public static void setResources(MessageSource resources) {
        CheckUtil.resources = resources;
    }

    public static void check(boolean condition, String msgKey, Object... args) {
        if (!condition) {
            fail(msgKey, args);
        }
    }

    public static void notEmpty(String str, String msgKey, Object... args) {
        if (str == null || str.isEmpty()) {
            fail(msgKey, args);
        }
    }

    public static void notNull(Object obj, String msgKey, Object... args) {
        if (obj == null) {
            fail(msgKey, args);
        }
    }

    private static void fail(String msgKey, Object... args) {
        throw new CheckException(resources.getMessage(msgKey, args, UserUtil.getLocale()));
    }

}
