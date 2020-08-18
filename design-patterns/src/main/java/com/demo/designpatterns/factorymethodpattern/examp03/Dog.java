package com.demo.designpatterns.factorymethodpattern.examp03;

/**
 * @ClassName Dog
 * @Description TODO
 * @Author hup
 * @Date 2020/8/13 19:06
 * @Version 1.0
 */
public abstract class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("dog eat meat!");
    }
}
