package com.demo.rabbitmq.demo.service;

import com.demo.rabbitmq.demo.common.ServerResponse;
import com.demo.rabbitmq.demo.pojo.Mail;

/**
 * @ClassName TestService
 * @Description
 * @Author H
 * @Date 2020/12/24 17:35
 * @Version 1.0
 */
public interface TestService {
    ServerResponse send(Mail mail);
}
