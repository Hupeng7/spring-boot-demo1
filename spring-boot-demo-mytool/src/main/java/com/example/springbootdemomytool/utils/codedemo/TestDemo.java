package com.example.springbootdemomytool.utils.codedemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestDemo
 * @Description
 * @Author hup
 * @Date 2020/8/18 17:06
 * @Version 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        Object o = list.stream().filter(e -> e > 0).findFirst().orElse(null);


    }
}
