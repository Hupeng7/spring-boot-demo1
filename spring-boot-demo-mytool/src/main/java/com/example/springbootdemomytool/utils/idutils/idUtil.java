package com.example.springbootdemomytool.utils.idutils;

import com.google.common.base.Strings;

/**
 * @ClassName idUtil
 * @Description
 * @Author H
 * @Date 2020/11/26 11:59
 * @Version 1.0
 */
public class idUtil {
    public static void main(String[] args) {
        String idStart = createIdStart(10, "1");
        System.out.println("idStart: " + idStart);  // 0000000001

        String idEnd = createIdEnd(10, "1001");
        System.out.println("idEnd: " + idEnd);

        // 限制长度小于 目标值长度   取目标值长度
        String idStart1 = createIdStart(2, "12345");
        System.out.println("idStart1: " + idStart1);


    }


    public static String createIdStart(int length, String value) {
        String result = Strings.padStart(value, length, '0');
        return result;
    }

    public static String createIdEnd(int length, String value) {
        String result = Strings.padEnd(value, length, '0');
        return result;
    }
}
