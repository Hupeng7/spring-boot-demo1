package com.demo.designpatterns.factorymethodpattern.examp03;

/**
 * @ClassName FemaleDog
 * @Description TODO
 * @Author hup
 * @Date 2020/8/13 19:09
 * @Version 1.0
 */
public class FemaleDog extends Dog {
    @Override
    public void gender() {
        System.out.println("I am a female Dog");
    }
}
