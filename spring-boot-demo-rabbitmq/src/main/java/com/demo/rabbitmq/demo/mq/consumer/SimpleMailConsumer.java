package com.demo.rabbitmq.demo.mq.consumer;

import com.demo.rabbitmq.demo.common.Constant;
import com.demo.rabbitmq.demo.config.RabbitMQConfig;
import com.demo.rabbitmq.demo.mq.MessageHelper;
import com.demo.rabbitmq.demo.pojo.Mail;
import com.demo.rabbitmq.demo.pojo.MsgLog;
import com.demo.rabbitmq.demo.service.MsgLogService;
import com.demo.rabbitmq.demo.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName SimpleMailConsumer
 * @Description
 * @Author H
 * @Date 2020/12/25 10:29
 * @Version 1.0
 */

@Component
@Slf4j
public class SimpleMailConsumer {
    @Autowired
    private MsgLogService msgLogService;
    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(queues = RabbitMQConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息 : {}", mail.toString());

        String msgId = mail.getMsgId();
        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {
            log.info("重复消费，msgId: {} ", msgId);
            return;
        }
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        boolean success = mailUtil.send(mail);
        if (success) {
            msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
            channel.basicAck(tag, false);
        } else {
            channel.basicNack(tag, false, true);
        }


    }


}
