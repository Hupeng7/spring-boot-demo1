package com.example.springbootdemomytool.utils.timeutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName DateByThreadLocalUtil
 * @Description
 * 线程安全- SimpleDateFormat.format()
 * 使用ThreadLocal 保证 SimpleDateFormat.format()线程安全
 * 2.3 使用ThreadLocal
 * 每个线程都将拥有自己的SimpleDateFormat对象副本。
 * @Author Leo
 * @Date 2020/5/22 15:16
 * @Version 1.0
 */
public class DateByThreadLocalUtil {

    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<>();

    public static Date parse(String str) throws Exception {
        SimpleDateFormat simpleDateFormat = local.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            local.set(simpleDateFormat);
        }
        return simpleDateFormat.parse(str);
    }

    public static String format(Date date) throws Exception {
        SimpleDateFormat simpleDateFormat = local.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            local.set(simpleDateFormat);
        }
        return simpleDateFormat.format(date);
    }


}
