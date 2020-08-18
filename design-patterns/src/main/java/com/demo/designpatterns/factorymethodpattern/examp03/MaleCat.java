package com.demo.designpatterns.factorymethodpattern.examp03;

/**
 * @ClassName MaleCat
 * @Description TODO
 * @Author hup
 * @Date 2020/8/13 19:08
 * @Version 1.0
 */
public class MaleCat extends Cat {
    @Override
    public void gender() {
        System.out.println("I am a male Cat");
    }
}
