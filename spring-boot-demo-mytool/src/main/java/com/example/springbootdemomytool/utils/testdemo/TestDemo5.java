package com.example.springbootdemomytool.utils.testdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @ClassName TestDemo5
 * @Description
 * @Author H
 * @Date 2021/3/2 16:52
 * @Version 1.0
 */
public class TestDemo5 {

    public static void main(String[] args) throws Exception {
        byte a = 1;
        Byte b = 1;
        System.out.println(a == b);

        byte c = 127;
        Integer d = 127;
        System.out.println(c == d);

        // 获取用键盘输入常用的两种方法
//        Scanner input = new Scanner(System.in);
//        String s = input.nextLine();
//        input.close();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();
        input.close();
    }
}
