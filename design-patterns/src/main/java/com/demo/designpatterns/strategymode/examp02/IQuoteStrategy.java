package com.demo.designpatterns.strategymode.examp02;

import java.math.BigDecimal;

/**
 * @ClassName IQuoteStrategy
 * @Description 报价策略接口
 * @Author hup
 * @Date 2020/8/10 11:46
 * @Version 1.0
 */
public interface IQuoteStrategy {
    // 获取折后价的价格
    BigDecimal getPrice(BigDecimal originalPrice);
}
