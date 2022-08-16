package com.demo.mqkafkaprovider.controller;

import com.demo.mqkafkaprovider.component.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName KafkaProController
 * @Description
 * @Author H
 * @Date 2022/8/16 9:46
 * @Version 1.0
 */
@RestController
@Slf4j
public class KafkaProController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/hello")
    public String hello() {
        log.info("--->test producer send message!");
        kafkaProducer.sendMessage();
        return "kafka message has send";
    }
}
