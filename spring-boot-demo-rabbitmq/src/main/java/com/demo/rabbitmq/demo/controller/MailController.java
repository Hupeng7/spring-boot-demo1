package com.demo.rabbitmq.demo.controller;

import com.demo.rabbitmq.demo.common.ServerResponse;
import com.demo.rabbitmq.demo.pojo.Mail;
import com.demo.rabbitmq.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MailController
 * @Description
 * @Author H
 * @Date 2020/12/24 17:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/mail")
@Slf4j
public class MailController {

    @Autowired
    private TestService testService;

    @PostMapping("/send")
    public ServerResponse sendMail(@Validated @RequestBody Mail mail, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }
        return testService.send(mail);
    }

    @PostMapping("/sendMany")
    public ServerResponse sendMailMany(@Validated @RequestBody Mail mail, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }
        for (int i = 0; i < 5; i++) {
            testService.send(mail);
            System.out.println("send mail : " + i);
        }
        return ServerResponse.success();
    }

}
