package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName PayContextWithAccount
 * @Description
 * @Author hup
 * @Date 2020/8/10 14:27
 * @Version 1.0
 */
public class PayContextWithAccount extends PayContext {
    // 银行账户
    private String account;

    public PayContextWithAccount(String username, double money, PayStrategy payStrategy, String account) {
        super(username, money, payStrategy);
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
