package com.demo.noifelse.demo4;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName AliPay
 * @Description
 * @Author H
 * @Date 2021/2/9 10:02
 * @Version 1.0
 */
@Service
public class AliPay implements IPay {
    @PostConstruct
    public void init() {
        PayStrategyFactory.register("aliPay", this);
    }

    @Override
    public void pay() {
        System.out.println("===send ali pay===");
    }
}
