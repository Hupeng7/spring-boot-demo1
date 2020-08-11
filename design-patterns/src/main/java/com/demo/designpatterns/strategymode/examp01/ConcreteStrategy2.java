package com.demo.designpatterns.strategymode.examp01;

/**
 * @ClassName ConcreteStrategy2
 * @Description 具体的策略实现2
 * @Author hup
 * @Date 2020/8/10 11:35
 * @Version 1.0
 */
public class ConcreteStrategy2 implements IStrategy {
    @Override
    public void algorithmMethod() {
        System.out.println("this is ConcreteStrategy2 method...");
    }
}
