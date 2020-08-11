package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName VIPCustomerQuoteStrategy
 * @Description VIP客户的报价策略实现
 * @Author hup
 * @Date 2020/8/10 11:52
 * @Version 1.0
 */
public class VIPCustomerQuoteStrategy implements IQuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！VIP客户享有8折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
