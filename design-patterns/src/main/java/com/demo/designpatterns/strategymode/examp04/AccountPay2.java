package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName AccountPay2
 * @Description
 * @Author hup
 * @Date 2020/8/10 14:36
 * @Version 1.0
 */
public class AccountPay2 implements PayStrategy {
    // 银行账户
    private String account;

    public AccountPay2(String account) {
        this.account = account;
    }

    @Override
    public void pay(PayContext ctx) {
        System.out.println("现在给：" + ctx.getUsername() + " 的账户：" + getAccount() + " 支付工资：" + ctx.getMoney() + " 元！");
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
