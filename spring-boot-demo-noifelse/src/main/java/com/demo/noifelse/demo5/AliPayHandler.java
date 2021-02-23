package com.demo.noifelse.demo5;

import org.springframework.stereotype.Service;

/**
 * @ClassName AliPayHandler
 * @Description
 * @Author H
 * @Date 2021/2/9 10:27
 * @Version 1.0
 */
@Service
public class AliPayHandler extends PayHandler {
    @Override
    public void pay(String code) {
        if ("ali".equals(code)) {
            System.out.println("===send ali pay===");
        } else {
            getNext().pay(code);
        }
    }
}
