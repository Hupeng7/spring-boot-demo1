package com.example.springbootdemomytool.utils.demo.reflectdecoratordemo;

/**
 * @ClassName FlyFeature
 * @Description
 * @Author hup
 * @Date 2020/8/18 18:24
 * @Version 1.0
 */
public class FlyFeature implements Feature {
    @Override
    public void load() {
        System.out.println("get fly feature...");
    }
}
