package com.example.springbootdemomytool.utils.testdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestDemo8
 * @Description
 * @Author H
 * @Date 2021/7/16 9:55
 * @Version 1.0
 */
public class TestDemo8 {
    public static void main(String[] args) {
        //replace();
    }

    public static void replace() {
        String date1 = "2021/07/16";
//        String date1 = null;
//        assert date1 != null : "date should not be null";
        String result = date1.replaceAll("\\/", "");
        System.out.println("result: " + result);
    }




}
