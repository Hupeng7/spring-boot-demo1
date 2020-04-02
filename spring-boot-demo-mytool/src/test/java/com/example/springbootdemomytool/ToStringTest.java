package com.example.springbootdemomytool;

/**
 * @ClassName ToStringTest
 * @Description
 * 耗时比较==============
 * String.valueOf(): 11ms
 * Integer.toString(): 3ms
 * i +"":15ms
 * ============================
 * @Author Leo
 * @Date 2020/4/2 14:55
 * @Version 1.0
 */
public class ToStringTest {

    public static void main(String[] args) {
        int loopTime = 50000;
        Integer i = 0;
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = String.valueOf(i);
        }
        System.out.println("String.valueOf(): " + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i.toString();
        }
        System.out.println("Integer.toString(): " + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int j = 0; j < loopTime; j++) {
            String str = i + "";
        }
        System.out.println("i +\"\":" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
