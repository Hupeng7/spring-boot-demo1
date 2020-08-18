package com.demo.designpatterns.factorymethodpattern.examp01;

/**
 * @ClassName Cat
 * @Description
 * @Author hup
 * @Date 2020/8/13 17:56
 * @Version 1.0
 */
public class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("cat eat fish!!!");
    }
}
