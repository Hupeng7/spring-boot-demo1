package com.demo.designpatterns.factorymethodpattern.examp03;

/**
 * @ClassName MaleDog
 * @Description TODO
 * @Author hup
 * @Date 2020/8/13 19:10
 * @Version 1.0
 */
public class MaleDog extends Dog {
    @Override
    public void gender() {
        System.out.println("I am a male Dog");
    }
}
