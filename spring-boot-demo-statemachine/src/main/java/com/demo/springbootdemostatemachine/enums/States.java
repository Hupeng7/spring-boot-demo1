package com.demo.springbootdemostatemachine.enums;

/**
 * @ClassName States
 * @Description  需求场景定义
 * @Author Leo
 * @Date 2020/5/13 17:03
 * @Version 1.0
 */
public enum States {
    APPLY,                // 请求
    UNPAID,               // 待支付
    WAITING_FOR_RECEIVE,  // 待收货
    DONE                  // 完成
}
