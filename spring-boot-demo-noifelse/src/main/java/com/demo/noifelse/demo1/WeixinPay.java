package com.demo.noifelse.demo1;

import com.demo.noifelse.demo2.PayCode;
import org.springframework.stereotype.Service;

/**
 * @ClassName WeixinPay
 * @Description
 * @Author H
 * @Date 2021/2/8 18:34
 * @Version 1.0
 */
@PayCode(value = "weixin", name = "微信支付")
@Service
public class WeixinPay implements IPay {
    @Override
    public void pay() {
        System.out.println("===send weixin pay===");
    }
}
