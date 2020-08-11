package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName QuoteContext
 * @Description 报价上下文角色
 * @Author hup
 * @Date 2020/8/10 11:55
 * @Version 1.0
 */
public class QuoteContext {
    // 持有一个具体的报价策略
    private IQuoteStrategy iQuoteStrategy;

    // 注入报价策略
    public QuoteContext(IQuoteStrategy iQuoteStrategy) {
        this.iQuoteStrategy = iQuoteStrategy;
    }

    // 回调具体报价策略的方法
    public BigDecimal getPrice(BigDecimal originalPrice) {
        return iQuoteStrategy.getPrice(originalPrice);
    }
}
