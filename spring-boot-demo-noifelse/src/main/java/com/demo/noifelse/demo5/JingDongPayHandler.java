package com.demo.noifelse.demo5;

import org.springframework.stereotype.Service;

/**
 * @ClassName JingDongPayHandler
 * @Description
 * @Author H
 * @Date 2021/2/9 14:06
 * @Version 1.0
 */
@Service
public class JingDongPayHandler extends PayHandler {
    @Override
    public void pay(String code) {
        if ("jingdong".equals(code)) {
            System.out.println("===send jingdong pay===");
        } else {
            getNext().pay(code);
        }
    }
}
