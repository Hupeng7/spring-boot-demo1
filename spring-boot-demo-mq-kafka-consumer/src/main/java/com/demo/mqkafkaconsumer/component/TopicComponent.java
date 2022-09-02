package com.demo.mqkafkaconsumer.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicComponent
 * @Description
 * @Author H
 * @Date 2022/8/16 11:37
 * @Version 1.0
 */
@Component
@Slf4j
public class TopicComponent {

    @KafkaListener(topics = {"hello"})
    public void handMessage(ConsumerRecord<String, String> record) {
        // 根据topic 获取值 处理业务
        String topic = record.topic();
        String value = record.value();
        log.info("consumer get message,topic:{},message:{}", topic, value);
    }

    @KafkaListener(topics = {"hello2"})
    public void onMessage(ConsumerRecord<String, String> record) {
        log.info("consumer get message,topic:{},message:{}", record.topic(), record.value());
    }
}
