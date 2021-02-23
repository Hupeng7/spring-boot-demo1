package com.demo.noifelse.demo1;

import com.demo.noifelse.demo2.PayCode;
import org.springframework.stereotype.Service;

/**
 * @ClassName AliPay
 * @Description
 * @Author H
 * @Date 2021/2/8 18:33
 * @Version 1.0
 */
@PayCode(value = "ali", name = "支付宝支付")
@Service
public class AliPay implements IPay {
    @Override
    public void pay() {
        System.out.println("===send ali pay===");
    }
}
