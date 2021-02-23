package com.demo.noifelse.demo4;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName JingDongPay
 * @Description
 * @Author H
 * @Date 2021/2/9 10:13
 * @Version 1.0
 */
@Service
public class JingDongPay implements IPay {
    @PostConstruct
    public void init() {
        PayStrategyFactory.register("jingDongPay", this);
    }

    @Override
    public void pay() {
        System.out.println("===send jingdong pay===");
    }
}
