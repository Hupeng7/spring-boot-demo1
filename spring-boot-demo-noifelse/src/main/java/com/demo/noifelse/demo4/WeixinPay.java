package com.demo.noifelse.demo4;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName WeixinPay
 * @Description
 * @Author H
 * @Date 2021/2/9 10:11
 * @Version 1.0
 */
@Service
public class WeixinPay implements IPay {
    @PostConstruct
    public void init() {
        PayStrategyFactory.register("weixinPay", this);
    }

    @Override
    public void pay() {
        System.out.println("===send weixin pay===");
    }
}
