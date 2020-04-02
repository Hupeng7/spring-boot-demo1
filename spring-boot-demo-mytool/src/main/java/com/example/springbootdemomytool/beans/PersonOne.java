package com.example.springbootdemomytool.beans;

/**
 * @ClassName PersonOne
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/1 17:20
 * @Version 1.0
 */
public class PersonOne {

    private static int a = 10;

    {
        System.out.println("普通代码块");
    }

    static {
        System.out.println("静态变量a:" + a);
        System.out.println("静态代码块");
    }


}
