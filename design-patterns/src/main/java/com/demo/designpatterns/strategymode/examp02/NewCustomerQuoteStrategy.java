package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName NewCustomerQuoteStrategy
 * @Description 新客户的报价策略实现类
 * @Author hup
 * @Date 2020/8/10 11:48
 * @Version 1.0
 */
public class NewCustomerQuoteStrategy implements IQuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("抱歉！新客户没有折扣！");
        return originalPrice;
    }
}
