package com.demo.designpatterns.templatemethodpattern;

/**
 * @ClassName ConcreteClass1
 * @Description
 * @Author hup
 * @Date 2020/8/19 12:02
 * @Version 1.0
 */
public class ConcreteClass1 extends AbstractClass {
    @Override
    protected void doSomething() {
        System.out.println("concrete class1 do something...");
    }

    @Override
    protected void doAnything() {
        System.out.println("concrete class1 do anything...");
    }
}
