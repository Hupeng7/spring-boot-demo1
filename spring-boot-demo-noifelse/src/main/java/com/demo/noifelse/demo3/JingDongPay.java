package com.demo.noifelse.demo3;

import org.springframework.stereotype.Service;

/**
 * @ClassName JingDong
 * @Description
 * @Author H
 * @Date 2021/2/8 19:05
 * @Version 1.0
 */
@Service
public class JingDongPay implements IPay {
    @Override
    public boolean support(String code) {
        return "jingdong".equals(code);
    }

    @Override
    public void pay() {
        System.out.println("===send jingdong pay===");
    }
}
