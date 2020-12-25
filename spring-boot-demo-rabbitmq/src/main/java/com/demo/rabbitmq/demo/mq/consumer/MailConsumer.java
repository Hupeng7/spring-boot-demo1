package com.demo.rabbitmq.demo.mq.consumer;

import com.demo.rabbitmq.demo.mq.BaseConsumer;
import com.demo.rabbitmq.demo.mq.MessageHelper;
import com.demo.rabbitmq.demo.pojo.Mail;
import com.demo.rabbitmq.demo.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName MailConsumer
 * @Description
 * @Author H
 * @Date 2020/12/23 17:19
 * @Version 1.0
 */
@Component
@Slf4j
public class MailConsumer implements BaseConsumer {
    @Autowired
    private MailUtil mailUtil;

    @Override
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());

        boolean success = mailUtil.send(mail);
        if (!success) {
            throw new RuntimeException("send mail error");
        }
    }
}
