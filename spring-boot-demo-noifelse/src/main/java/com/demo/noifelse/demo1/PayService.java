package com.demo.noifelse.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PayService
 * @Description 错误范例
 * @Author H
 * @Date 2021/2/8 18:36
 * @Version 1.0
 */
@Service
public class PayService {
    @Autowired
    private AliPay aliPay;

    @Autowired
    private WeixinPay weixinPay;

    @Autowired
    private JingDongPay jingDongPay;

    public void toPay(String code) {
        if ("ali".equals(code)) {
            aliPay.pay();
        } else if ("weixin".equals(code)) {
            weixinPay.pay();
        } else if ("jingdong".equals(code)) {
            jingDongPay.pay();
        } else {
            System.out.println("can not find pay code");
        }
    }

}
