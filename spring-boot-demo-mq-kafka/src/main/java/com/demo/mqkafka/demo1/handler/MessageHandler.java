package com.demo.mqkafka.demo1.handler;

import com.demo.mqkafka.demo1.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @ClassName MessageHandler
 * @Description
 * @Author H
 * @Date 2022/8/11 11:39
 * @Version 1.0
 */
@Component
@Slf4j
public class MessageHandler {

    @KafkaListener(topics = KafkaConsts.TOPIC_TEST, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

}
