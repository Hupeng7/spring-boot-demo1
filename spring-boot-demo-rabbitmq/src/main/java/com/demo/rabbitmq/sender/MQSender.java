package com.demo.rabbitmq.sender;

import com.demo.rabbitmq.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName MQSender
 * @Description
 * @Author H
 * @Date 2020/12/22 17:58
 * @Version 1.0
 */
@Component
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData: " + correlationData);
            System.out.println("ack: " + ack);
            if (!ack) {
                System.out.println("exception handler...");
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange: " + exchange + ",routingKey: "
                    + routingKey + ",replyCode: " + replyCode + ",replyText: " + replyText);
        }
    };

    // 发送消息方法调用： 构建Message消息
    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageProperties messageProperties = new MessageProperties();
        // 在生产环境中这里不用Message，而是使用fastJson等工具将对象转换为 json 格式发送
        Message msg = new Message(message.toString().getBytes(), messageProperties);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        // id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890" + new Date());
        rabbitTemplate.convertAndSend("BOOT-EXCHANGE-1", "boot.save", msg, correlationData);
    }

    // 发送消息方法调用： 构建Message消息
    public void sendUser(User user) throws Exception {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        // id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890" + new Date());
        rabbitTemplate.convertAndSend("BOOT-EXCHANGE-1", "boot.save", user, correlationData);

    }


}
