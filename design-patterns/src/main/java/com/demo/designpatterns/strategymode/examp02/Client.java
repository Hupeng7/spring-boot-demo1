package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName Client
 * @Description 外部客户端
 * @Author hup
 * @Date 2020/8/10 12:04
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("*********************************");
        // 1.创建老客户的报价策略
        IQuoteStrategy iQuoteStrategyNew = new NewCustomerQuoteStrategy();
        // 2.创建报价上下文对象，并设置具体的报价策略
        QuoteContext quoteContext = new QuoteContext(iQuoteStrategyNew);
        // 3.调用报价上下文的方法
        BigDecimal priceNew = quoteContext.getPrice(new BigDecimal(100));
        System.out.println("折扣价为：" + priceNew);
        System.out.println("*********************************");
        System.out.println("老客折扣价为: " + new QuoteContext(new OldCustomerQuoteStrategy()).getPrice(new BigDecimal(100)));
        System.out.println("*********************************");
        System.out.println("VIP折扣价为： " + new QuoteContext(new VIPCustomerQuoteStrategy()).getPrice(new BigDecimal(100)));

        System.out.println("*********************************");
        IQuoteStrategy mvpQuoteStrategy = new MVPCustomerQuoteStrategy();
        QuoteContext quoteContext1 = new QuoteContext(mvpQuoteStrategy);
        BigDecimal priceMvp = quoteContext1.getPrice(new BigDecimal(100));
        System.out.println("mvp price : " + priceMvp);

    }
}
