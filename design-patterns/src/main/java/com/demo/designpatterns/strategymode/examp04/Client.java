package com.demo.designpatterns.strategymode.examp04;

/**
 * @ClassName Client
 * @Description 外部客户端
 * @Author hup
 * @Date 2020/8/10 14:21
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        // 创建具体的支付策略
        PayStrategy rmbStrategy = new RMBPay();
        PayStrategy dollarStrategy = new DollarPay();
        // 准备小王的支付上下文
        PayContext ctx = new PayContext("小王", 30000, rmbStrategy);
        ctx.pay();
        // 准备jack的支付上下文
        ctx = new PayContext("jack", 10000, dollarStrategy);
        ctx.pay();

        // 创建支付到银行账户的支付策略
        PayStrategy accountStrategy = new AccountPay();
        ctx = new PayContextWithAccount("xiao zhang", 40000, accountStrategy, "1234567890");
        ctx.pay();

        PayStrategy accountStrategy2 = new AccountPay2("6666666666666");
        ctx = new PayContext("xiao li", 40000, accountStrategy2);
        ctx.pay();
    }
}
