package com.example.springbootdemomytool;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test01
 * @Description
 * @Author hup
 * @Date 2020/8/11 15:04
 * @Version 1.0
 */
public class Test01 {
    public static void main(String[] args) {
        String i = -1 % 2 == 1 ? "奇数" : "偶数";
        System.out.println(i);

        String ii = -1 % 2 == 0 ? "偶数" : "奇数";
        System.out.println(ii);

        boolean equals = StringUtils.remove("$是$", "$").equals("是");
        System.out.println("equals===>" + equals);
        String s = "$是$".replaceAll("\\$", "是");
        String s2 = "$是$".replaceAll("$", "是");
        String s1 = "$是$".replace("$", "是");
        System.out.println("s: " + s);
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
//        s: 是是是
//        s1: 是是是
//        s2: $是$是

        Map<String, Object> request_param = new HashMap<String, Object>();
        request_param.put("request", "fasdfajk");

        System.out.println(request_param.toString());

        long l = Long.MAX_VALUE;
        System.out.println(l);
        System.out.println(l + 1L);

        BigInteger bigInteger = new BigInteger("9223372036854775800");
        BigInteger bigInteger1 = new BigInteger("100");
        System.out.println(bigInteger.add(bigInteger1));

    }
}
