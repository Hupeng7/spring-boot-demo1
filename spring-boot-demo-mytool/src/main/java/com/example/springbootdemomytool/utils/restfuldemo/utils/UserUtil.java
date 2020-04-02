package com.example.springbootdemomytool.utils.restfuldemo.utils;

import org.slf4j.MDC;

import java.util.Locale;

/**
 * @ClassName UserUtil
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/1 15:51
 * @Version 1.0
 */
public class UserUtil {
    private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();

    private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
        @Override
        protected Locale initialValue() {
            // 语言的默认值
            return Locale.CHINESE;
        };
    };

    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(String userid) {
        tlUser.set(userid);

        // 把用户信息放到log4j
        MDC.put(KEY_USER, userid);
    }

    public static String getUser() {
        return tlUser.get();
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();

        MDC.remove(KEY_USER);
    }
}
