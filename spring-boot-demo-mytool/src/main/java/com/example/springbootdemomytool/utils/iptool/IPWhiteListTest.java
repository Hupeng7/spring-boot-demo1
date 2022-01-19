package com.example.springbootdemomytool.utils.iptool;

/**
 * @ClassName IPWhiteListTest
 * @Description
 * @Author H
 * @Date 2022/1/19 10:50
 * @Version 1.0
 */
public class IPWhiteListTest {

    public static void main(String[] args) {
        String ipWhile = "192.168.1.1;" +        // 设置单个IP的白名单
                "192.168.2.*;" +                 // 设置ip通配符，对一个ip段进行匹配
                "192.168.3.17-192.168.3.38";     // 设置一个IP范围

        boolean flag = IPWhiteList.checkLoginIP("192.168.2.2", ipWhile);
        boolean flag2 = IPWhiteList.checkLoginIP("192.168.1.2", ipWhile);
        boolean flag3 = IPWhiteList.checkLoginIP("192.168.3.16", ipWhile);
        boolean flag4 = IPWhiteList.checkLoginIP("192.168.3.17", ipWhile);

        System.out.println(flag);   // true
        System.out.println(flag2);  // false
        System.out.println(flag3);  // false
        System.out.println(flag4);  // true
    }

}
