package com.example.springbootdemomytool.utils.testdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestDemo9
 * @Description
 * @Author H
 * @Date 2023/1/10 15:58
 * @Version 1.0
 */
public class TestDemo9 {

    public static void main(String[] args) {
        int i = test1("414");
        System.out.println("i======>" + i);

        String loanNo = "HXHT1002892026081210042156001";
        System.out.println(loanNo.substring(2));

    }

    private static int test1(String item) {
        String[] a = new String[]{"44", "22", "33", "11"};

//        List<String> b = new ArrayList<String>() {
//            {
//                add("44");
//                add("22");
//                add("33");
//                add("11");
//            }
//        };
        List<String> b = Arrays.asList(a);
        List<String> c = new ArrayList<>(Arrays.asList(a));

        int a1 = b.indexOf(item);
        return a1;
    }


}
