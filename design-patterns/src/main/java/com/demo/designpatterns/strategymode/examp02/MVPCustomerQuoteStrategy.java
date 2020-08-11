package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName MVPCustomerQuoteStrategy
 * @Description TODO
 * @Author hup
 * @Date 2020/8/10 13:45
 * @Version 1.0
 */
public class MVPCustomerQuoteStrategy implements IQuoteStrategy {
    @Override
    public BigDecimal getPrice(BigDecimal originalPrice) {
        System.out.println("恭喜！MVP客户享受7折优惠！");
        originalPrice = originalPrice.multiply(new BigDecimal(0.7)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }
}
