package com.example.springbootdemomytool.utils.obejctutilsdemo;

import org.springframework.util.ObjectUtils;

/**
 * @ClassName ObjectUtilsDemo
 * @Description
 * @Author H
 * @Date 2021/7/6 13:37
 * @Version 1.0
 */
public class ObjectUtilsDemo {

    public static void main(String[] args) {
        boolean equalsResult = equals("", null);
        System.out.println("equals result:" + equalsResult);
    }

    public static boolean equals(String name, Object value) {
        if (ObjectUtils.nullSafeEquals(name, value)) {
            return true;
        }
        return false;
    }

    public static boolean equals1(){
        return true;
    }
}
