package com.demo.noifelse.demo3;

import org.springframework.stereotype.Service;

/**
 * @ClassName WeixinPay
 * @Description
 * @Author H
 * @Date 2021/2/8 19:04
 * @Version 1.0
 */
@Service
public class WeixinPay implements IPay {
    @Override
    public boolean support(String code) {
        return "weixin".equals(code);
    }

    @Override
    public void pay() {
        System.out.println("===send weixin pay===");
    }
}
