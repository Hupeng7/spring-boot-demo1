package com.example.springbootdemomytool.utils.testdemo;

import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName TestDemo4
 * @Description
 * @Author H
 * @Date 2021/2/23 18:20
 * @Version 1.0
 */
public class TestDemo4 {
    public static void main(String[] args) {
        // Arrays.asList()
//        boolean contains = Arrays.asList("1", "2").contains("3");
//        System.out.println(contains);
//        boolean contains1 = Arrays.asList("1", "2").contains(null);
//        System.out.println(contains1);
//
//        int len = "yqgcLZS000000004".length();
//        String str = "yqgcLZS000000004I9/ZM/Vz41vjfonEQWfmh9t0jYo3CpTCE8FHnv6VKPlK+SqMeeIWEMb4fJ6IaiOlktDE8BBs9r7JYfJm7corgVDI71vAQ6BOCqVyTHeWSw0FbiEdYedx2kbfwtj4girUy4ZBVrHhUahd1fk82AFjNRWjBGTC1MOSewIDBpwKdOZ7w9v45vkyOmnG4WZF9yUSIhcj6Pguksq60CXBPwBtqU3Tl1O5iUHb2Uf4AdAgo4t9f7+R0OfZRpmg6d54Oej5risuXXjaR4BMZWX/ZWfaDJIe8RY7OE403iP+dYCJ8F/pZcEv93sCXfm62WgBzYK+1bPlIoj/rljJb76o+ofxCqsb/i7pAgE9Nk+NW4FE4DOTshKtzDt14d1AVW2jK79VyJiBI71PxniuTjt5euwxns8wG3Av84v1quq6RoHlF4NaVVWXH0Ck9K0qm+SWYXX2qi86dmBsqJ4oojgxYYscoCjL4bDmaLr3QheC+GKajCfBZGBcJ9zecdHAmWh2+7hpJcVHJpnnWHukSFrYh86RVpFhpD838WIKYC5Qm+VAey4=";
//        String substring = str.substring(len);
//        System.out.println(substring);

        String t1 = "10005XHKJ20210225Y0000007|10401000071405891|1318.63|1300|17.56|0.09|0.98|20231225|0|回购成功|0270085020000351060|1318.63|0270085020000351060|0|1|YD/25";
        String t2 = "10005XHKJ20210225Y0000008|10401000071406081|710.03|700|9.46|0.05|0.52|20231225|0|回购成功|0270085020000351060|710.03||0|0| ";

//        String[] split1 = t1.split("\\|",-1);
//        for (int i = 0; i < split1.length; i++) {
//            System.out.println(i+":"+split1[i]);
//        }
//
//        System.out.println(":ab:cd:ef::".split(":",-1).length);//不忽略任何一个分隔符
//
//        String[] split2 = t2.split("\\|",-1);
//        for (int i = 0; i < split2.length; i++) {
//            System.out.println(i+":"+split2[i]);
//        }

        String sp = "boo:aoo:foo";
        String[] sp1 = sp.split(":", 4);
        for (int i = 0; i < 3; i++) {

        }

        Map<String, String> map = new HashMap<>();
        map.put("1", "111");
        map.put("2", "222");
        map.put("3", "333");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }

    }

    private static void testAdd(List<T> list) {
        // list.add("hello");
        // list.add(null);
        T t = list.get(0);
    }

}
