package com.example.springbootdemomytool.utils.demo.reflectdecoratordemo;

/**
 * @ClassName Rat
 * @Description
 * @Author hup
 * @Date 2020/8/18 18:22
 * @Version 1.0
 */
public class Rat implements Animal {
    @Override
    public void doStuff() {
        System.out.println("rat do some base thing...");
    }
}
