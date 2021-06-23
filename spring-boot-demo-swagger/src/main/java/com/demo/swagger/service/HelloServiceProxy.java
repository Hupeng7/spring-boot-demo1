package com.demo.swagger.service;

/**
 * @ClassName HelloServiceProxy
 * @Description
 * @Author H
 * @Date 2021/6/17 15:25
 * @Version 1.0
 */

public class HelloServiceProxy implements HelloService {

    private HelloService target;

    public HelloServiceProxy(HelloService target) {
        this.target = target;
    }

    @Override
    public void say() {
        System.out.println("记录日志,proxy start");
        target.say();
        System.out.println("清理数据,proxy end");
    }
}
