package com.demo.designpatterns.factorymethodpattern.examp02;

import com.demo.designpatterns.factorymethodpattern.examp01.Animal;

/**
 * @ClassName Dog
 * @Description
 * @Author hup
 * @Date 2020/8/13 17:57
 * @Version 1.0
 */
public class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("dog eat meat!!!");
    }
}
