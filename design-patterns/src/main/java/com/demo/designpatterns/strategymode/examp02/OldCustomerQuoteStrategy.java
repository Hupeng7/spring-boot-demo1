package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName OldCustomerQuoteStrategy
 * @Description 老客户的报价策略
 * @Author hup
 * @Date 2020/8/10 11:49
 * @Version 1.0
 */
public class OldCustomerQuoteStrategy implements IQuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！老客户享有9折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
