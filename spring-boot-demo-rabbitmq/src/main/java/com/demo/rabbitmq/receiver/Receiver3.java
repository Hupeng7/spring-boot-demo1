package com.demo.rabbitmq.receiver;

import com.demo.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Receiver
 * @Description
 * @Author H
 * @Date 2020/12/21 19:00
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "user")
public class Receiver3 {

    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver3 : " + user);
    }

}
