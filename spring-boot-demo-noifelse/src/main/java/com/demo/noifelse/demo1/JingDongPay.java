package com.demo.noifelse.demo1;

import com.demo.noifelse.demo2.PayCode;
import org.springframework.stereotype.Service;

/**
 * @ClassName JingDongPay
 * @Description
 * @Author H
 * @Date 2021/2/8 18:35
 * @Version 1.0
 */
@PayCode(value = "jingdong", name = "京东支付")
@Service
public class JingDongPay implements IPay {
    @Override
    public void pay() {
        System.out.println("===send jingdong pay===");
    }
}
