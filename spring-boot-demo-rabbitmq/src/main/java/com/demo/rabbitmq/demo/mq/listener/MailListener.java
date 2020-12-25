package com.demo.rabbitmq.demo.mq.listener;

import com.demo.rabbitmq.demo.config.RabbitMQConfig;
import com.demo.rabbitmq.demo.mq.consumer.MailConsumer;
import com.demo.rabbitmq.demo.mq.consumer.SimpleMailConsumer;
import com.demo.rabbitmq.demo.service.MsgLogService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName MailListener
 * @Description
 * @Author H
 * @Date 2020/12/23 17:20
 * @Version 1.0
 */
@Component
public class MailListener {
    @Autowired
    private MailConsumer mailConsumer;
    @Autowired
    private MsgLogService msgLogService;
    @Autowired
    private SimpleMailConsumer simpleMailConsumer;

//    @RabbitListener(queues = RabbitMQConfig.MAIL_QUEUE_NAME)
//    public void consume(Message message, Channel channel) throws IOException {
//        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
//        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
//        if (null != proxy) {
//            System.out.println("consume1------>");
//            proxy.consume(message, channel);
//        }
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.MAIL_QUEUE_NAME)
//    public void consume2(Message message, Channel channel) throws IOException {
//        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
//        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
//        if (null != proxy) {
//            System.out.println("consume2------>");
//            proxy.consume(message, channel);
//        }
//    }

    @RabbitListener(queues = RabbitMQConfig.MAIL_QUEUE_NAME)
    public void consume3(Message message, Channel channel) throws IOException {
        System.out.println("consume3------>");
        simpleMailConsumer.consume(message, channel);
    }


}
