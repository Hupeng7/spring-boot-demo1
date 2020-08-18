package com.demo.designpatterns.factorymethodpattern.examp03;

/**
 * @ClassName Cat
 * @Description TODO
 * @Author hup
 * @Date 2020/8/13 19:05
 * @Version 1.0
 */
public abstract class Cat extends Animal {

    @Override
    public void eat() {
        System.out.println("cat eat fish!");
    }
}
