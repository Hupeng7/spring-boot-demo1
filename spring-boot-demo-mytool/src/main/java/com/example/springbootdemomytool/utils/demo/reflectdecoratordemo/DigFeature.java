package com.example.springbootdemomytool.utils.demo.reflectdecoratordemo;

/**
 * @ClassName DigFeature
 * @Description
 * @Author hup
 * @Date 2020/8/18 18:25
 * @Version 1.0
 */
public class DigFeature implements Feature {
    @Override
    public void load() {
        System.out.println("get dig feature...");
    }
}
