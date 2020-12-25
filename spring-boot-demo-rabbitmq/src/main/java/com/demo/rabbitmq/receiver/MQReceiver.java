package com.demo.rabbitmq.receiver;

import com.demo.rabbitmq.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName MQReceiver
 * @Description
 * @Author H
 * @Date 2020/12/22 18:20
 * @Version 1.0
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "boot.queue1", durable = "true"),
                exchange = @Exchange(value = "BOOT-EXCHANGE-1", type = "topic", durable = "true", ignoreDeclarationExceptions = "true"),
                key = "boot.*"
        )
)
public class MQReceiver {
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 手动ack
        channel.basicAck(deliveryTag, true);
        System.out.println("receive--1: " + new String(message.getBody()));
    }

    @RabbitHandler
    public void onUserMessage(@Payload User user, Channel channel, @Headers Map<String, Object> headers) throws IOException {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手动ack
        channel.basicAck(deliveryTag, true);
        System.out.println("receive--11: " + user.toString());
    }


}
