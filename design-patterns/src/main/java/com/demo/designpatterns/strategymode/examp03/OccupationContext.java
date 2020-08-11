package com.demo.designpatterns.strategymode.examp03;

/**
 * @ClassName OccupationContext
 * @Description 由上下文来选择具体的策略
 * @Author hup
 * @Date 2020/8/10 14:03
 * @Version 1.0
 */
public class OccupationContext {
    public void occupationWestOfSiChuan(String msg) {
        IOccupationStrategyWestOfSiChuan strategy = new UpperStrategy();
        try {
            strategy.occupationWestOfSiChuan(msg);
        } catch (Exception e) {
            // 上计有问题行不通之后 用中计策
            strategy = new MiddleStrategy();
            strategy.occupationWestOfSiChuan(msg);
        }
    }

}
