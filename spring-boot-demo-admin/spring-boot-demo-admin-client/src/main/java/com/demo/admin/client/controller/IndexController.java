package com.demo.admin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/31 18:01
 * @Version 1.0
 */
@RestController
@Slf4j
public class IndexController {
    @GetMapping(value = {"", "/"})
    public String index() {
        log.info("get request!!!");
        return "This is a Spring Boot Admin Client.";
    }
}
