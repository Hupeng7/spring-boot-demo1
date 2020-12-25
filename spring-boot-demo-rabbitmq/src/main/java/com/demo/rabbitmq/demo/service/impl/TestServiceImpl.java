package com.demo.rabbitmq.demo.service.impl;

import com.demo.rabbitmq.demo.common.ResponseCode;
import com.demo.rabbitmq.demo.common.ServerResponse;
import com.demo.rabbitmq.demo.config.RabbitMQConfig;
import com.demo.rabbitmq.demo.mapper.MsgLogMapper;
import com.demo.rabbitmq.demo.mq.MessageHelper;
import com.demo.rabbitmq.demo.pojo.Mail;
import com.demo.rabbitmq.demo.pojo.MsgLog;
import com.demo.rabbitmq.demo.service.TestService;
import com.demo.rabbitmq.demo.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author H
 * @Date 2020/12/24 17:41
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private MsgLogMapper msgLogMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitMQConfig.MAIL_EXCHANGE_NAME, RabbitMQConfig.MAIL_ROUTING_KEY_NAME);
        // 消息入库
        msgLogMapper.insert(msgLog);

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.MAIL_EXCHANGE_NAME, RabbitMQConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }
}
