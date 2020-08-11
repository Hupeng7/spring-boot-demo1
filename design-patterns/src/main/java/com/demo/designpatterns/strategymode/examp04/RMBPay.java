package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName RMBPay
 * @Description 人民币支付
 * @Author hup
 * @Date 2020/8/10 14:14
 * @Version 1.0
 */
public class RMBPay implements PayStrategy {
    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给：" + ctx.getUsername() + " 人民币支付 " + ctx.getMoney() + " 元！");
    }
}
