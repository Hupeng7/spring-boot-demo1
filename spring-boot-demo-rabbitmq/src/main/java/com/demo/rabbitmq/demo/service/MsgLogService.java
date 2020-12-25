package com.demo.rabbitmq.demo.service;

import com.demo.rabbitmq.demo.pojo.MsgLog;

/**
 * @ClassName MsgLogService
 * @Description
 * @Author H
 * @Date 2020/12/23 17:25
 * @Version 1.0
 */
public interface MsgLogService {
    void updateStatus(String msgId, Integer deliverSuccess);

    MsgLog selectByMsgId(String correlationId);
}
