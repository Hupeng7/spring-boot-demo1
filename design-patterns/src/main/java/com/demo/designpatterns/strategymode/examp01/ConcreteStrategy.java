package com.demo.designpatterns.strategymode.examp01;

/**
 * @ClassName ConcreteStrategy
 * @Description 具体的策略实现
 * @Author hup
 * @Date 2020/8/10 11:33
 * @Version 1.0
 */
public class ConcreteStrategy implements IStrategy {
    // 具体的策略实现
    @Override
    public void algorithmMethod() {
        System.out.println("this is ConcreteStrategy method ...");
    }
}
