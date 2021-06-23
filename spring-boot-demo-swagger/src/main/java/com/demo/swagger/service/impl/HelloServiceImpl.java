package com.demo.swagger.service.impl;

import com.demo.swagger.service.HelloService;

/**
 * @ClassName HelloServiceImpl
 * @Description
 * @Author H
 * @Date 2021/6/17 15:21
 * @Version 1.0
 */
//@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void say() {
        System.out.println("hello world,you got implement");
    }
}
