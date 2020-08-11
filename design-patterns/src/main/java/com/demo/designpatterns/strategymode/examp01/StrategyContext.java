package com.demo.designpatterns.strategymode.examp01;

/**
 * @ClassName StrategyContext
 * @Description 策略上下文
 * @Author hup
 * @Date 2020/8/10 11:36
 * @Version 1.0
 */
public class StrategyContext {
    // 持有一个策略实现的引用
    private IStrategy strategy;

    // 使用构造器注入具体的策略类
    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void contextMethod() {
        // 调用策略实现的方法
        strategy.algorithmMethod();
    }


}
