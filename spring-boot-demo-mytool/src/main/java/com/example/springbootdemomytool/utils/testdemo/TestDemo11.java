package com.example.springbootdemomytool.utils.testdemo;

/**
 * @ClassName TestDemo11
 * @Description
 * @Author H
 * @Date 2024/11/27 11:11
 * @Version 1.0
 */
public class TestDemo11 {
    public static void main(String[] args) {

        methodOne(null);
    }

    public static void methodOne(String param) {
        if (param == null || param.isEmpty()) {
            System.out.println("param can not null");
            return;
        }
        switch (param) {
            case "sth":
                System.out.println("it is sth.");
                break;
            case "null":
                System.out.println("it is null");
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
