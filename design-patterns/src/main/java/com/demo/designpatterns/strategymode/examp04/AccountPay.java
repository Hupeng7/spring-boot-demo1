package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName AccountPay
 * @Description
 * @Author hup
 * @Date 2020/8/10 14:27
 * @Version 1.0
 */
public class AccountPay implements PayStrategy {
    @Override
    public void pay(PayContext ctx) {
        PayContextWithAccount ctxAccount = (PayContextWithAccount) ctx;
        System.out.println("现在给：" + ctxAccount.getUsername() + " 的账户：" + ctxAccount.getAccount() + " 支付工资：" + ctxAccount.getMoney() + " 元！");
    }
}
