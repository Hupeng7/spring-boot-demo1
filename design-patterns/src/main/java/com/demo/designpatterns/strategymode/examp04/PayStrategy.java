package com.demo.designpatterns.strategymode.examp04;

// 支付策略接口
public interface PayStrategy {
    // 在支付策略接口的支付方法中含有支付上下文作为参数，以便在具体的支付策略中回调上下文中的方法获取数据
    public void pay(PayContext ctx);
}
