package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName DollarPay
 * @Description 美元支付策略
 * @Author hup
 * @Date 2020/8/10 14:18
 * @Version 1.0
 */
public class DollarPay implements PayStrategy {
    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给：" + ctx.getUsername() + " 美元支付 " + ctx.getMoney() + " dollar!");
    }
}
