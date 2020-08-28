package com.demo.designpatterns.templatemethodpattern;

/**
 * @ClassName ConcreteClass2
 * @Description
 * @Author hup
 * @Date 2020/8/19 12:04
 * @Version 1.0
 */
public class ConcreteClass2 extends AbstractClass {
    @Override
    protected void doSomething() {
        System.out.println("concrete class2 do something...");
    }

    @Override
    protected void doAnything() {
        System.out.println("concrete class2 do anything...");
    }
}
