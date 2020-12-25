package com.demo.rabbitmq.demo.common;

/**
 * @ClassName Constant
 * @Description
 * @Author H
 * @Date 2020/12/23 17:31
 * @Version 1.0
 */
public class Constant {

    public interface MsgLogStatus {
        // 消息投递中
        Integer DELIVERING = 0;
        // 投递成功
        Integer DELIVER_SUCCESS = 1;
        // 投递失败
        Integer DELIVER_FAIL = 2;
        // 已消费
        Integer CONSUMED_SUCCESS = 3;
    }
}
