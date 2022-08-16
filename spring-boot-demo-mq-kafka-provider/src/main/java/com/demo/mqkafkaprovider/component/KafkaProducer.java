package com.demo.mqkafkaprovider.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName KafkaProducer
 * @Description 生产消息发送者
 * @Author H
 * @Date 2022/8/16 9:46
 * @Version 1.0
 */
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() {
        try {
            // 生产消息
            LocalDateTime date = LocalDateTime.now();
            String nowFormat = formatDate(date);

            String message = "hello!test kafka." + nowFormat;
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("hello", "hello", message);
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("sendMessage success");
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("sendMessage error");
                }
            });
        } catch (Exception e) {
            log.error("sendMessage exception,{}", e);
        }
    }

    /**
     * JDK8 时间格式化
     *
     * @param date
     * @return
     * @throws Exception
     */
    private String formatDate(LocalDateTime date) throws Exception {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 日期转字符串
        return date.format(format);
    }

}
