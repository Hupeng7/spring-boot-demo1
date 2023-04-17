package com.example.springbootdemomytool.utils.stringdemo;

/**
 * @ClassName StringDemo02
 * @Description

 * @Author H
 * @Date 2023/4/13 17:28
 * @Version 1.0
 */
public class StringDemo02 {

    public static void main(String[] args) {
        stringSplitTest1();
    }

    /**
     * String中的split(",")和split(",",-1)的区别
     * 1、当字符串最后一位有值时，两者没有区别
     * 2、当字符串最后一位或者N位是分隔符时，前者不会继续切分，而后者继续切分。即前者不保留null值，后者保留。
     */
    private static void stringSplitTest1() {
        String line = "hello,,world,,,";

        String[] res1 = line.split(",");
        String[] res2 = line.split(",", -1);

        int i = 1;
        for (String s : res1) {
            System.out.println(i++ +":"+ s);
        }

        System.out.println("=============");
        i = 1;
        for (String s : res2) {
            System.out.println(i++ +":"+ s);
        }
    }

}
