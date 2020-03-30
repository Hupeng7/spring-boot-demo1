package com.example.springbootdemofilterinterceptor.interceptors.controller;

import com.example.springbootdemofilterinterceptor.interceptors.LoginRequired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 14:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    @GetMapping("/hello1/{word}")
    @LoginRequired
    public String hello1(@PathVariable String word) {
        System.out.println("hello!" + word);
        return "hello!" + word;
    }
}

