package com.demo.noifelse.demo3;

import org.springframework.stereotype.Service;

/**
 * @ClassName AliPay
 * @Description
 * @Author H
 * @Date 2021/2/8 19:02
 * @Version 1.0
 */
@Service
public class AliPay implements IPay {
    @Override
    public boolean support(String code) {
        return "ali".equals(code);
    }

    @Override
    public void pay() {
        System.out.println("===send ali pay===");
    }
}
