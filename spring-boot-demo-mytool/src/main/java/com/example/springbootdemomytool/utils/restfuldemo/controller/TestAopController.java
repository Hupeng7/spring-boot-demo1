package com.example.springbootdemomytool.utils.restfuldemo.controller;

import com.example.springbootdemomytool.utils.restfuldemo.beans.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/31 10:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/aoptest/")
@Slf4j
@Component
public class TestAopController {

    @GetMapping("/test1")
    public ResultBean<String> hello() {
        System.out.println("controller aop test================");
        return new ResultBean<String>("well done");
    }


}
