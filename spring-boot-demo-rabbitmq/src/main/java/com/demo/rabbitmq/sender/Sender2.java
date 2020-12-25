package com.demo.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Sender
 * @Description
 * @Author H
 * @Date 2020/12/21 18:57
 * @Version 1.0
 */
@Component
public class Sender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "hello2 " + " ****** " + i;
        // System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);

    }

}
