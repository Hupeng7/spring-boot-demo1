package com.demo.springboothelloworld01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author hup
 * @Date 2020/7/21 17:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    @GetMapping("")
    public String hello() {
        log.info("request success,time : {}", new Date());
        return "Hello world!" + new Date();
    }
}
