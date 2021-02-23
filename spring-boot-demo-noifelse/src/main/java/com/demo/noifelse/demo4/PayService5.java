package com.demo.noifelse.demo4;

import org.springframework.stereotype.Service;

/**
 * @ClassName PayService5
 * @Description 策略+工厂模式
 * @Author H
 * @Date 2021/2/9 10:21
 * @Version 1.0
 */
@Service
public class PayService5 {

    public void toPay(String code) {
        PayStrategyFactory.get(code).pay();
    }
}
