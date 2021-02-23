package com.demo.noifelse.demo5;

import org.springframework.stereotype.Service;

/**
 * @ClassName WeixinPayHandler
 * @Description
 * @Author H
 * @Date 2021/2/9 14:04
 * @Version 1.0
 */
@Service
public class WeixinPayHandler extends PayHandler {

    @Override
    public void pay(String code) {
        if ("weixin".equals(code)) {
            System.out.println("===send weixin pay===");
        } else {
            getNext().pay(code);
        }
    }
}
