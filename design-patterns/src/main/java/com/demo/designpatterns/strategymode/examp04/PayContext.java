package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName PayContext
 * @Description 支付上下文，含有多个算法的共有数据
 * @Author hup
 * @Date 2020/8/10 14:13
 * @Version 1.0
 */
public class PayContext {
    private String username;
    private double money;
    private PayStrategy payStrategy;

    public void pay() {
        payStrategy.pay(this);
    }

    public PayContext(String username, double money, PayStrategy payStrategy) {
        this.username = username;
        this.money = money;
        this.payStrategy = payStrategy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
