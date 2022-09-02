package com.example.springbootdemomytool.utils.reflectDemo.demo2;

/**
 * @ClassName TargetObject
 * @Description
 * @Author H
 * @Date 2022/8/17 17:36
 * @Version 1.0
 */
public class TargetObject {
    private String value;

    public TargetObject() {
        value = "this is value";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
