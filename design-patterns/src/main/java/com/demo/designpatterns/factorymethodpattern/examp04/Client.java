package com.demo.designpatterns.factorymethodpattern.examp04;

/**
 * @ClassName Client
 * @Description 通用工厂方法模式代码
 * @Author hup
 * @Date 2020/8/17 18:23
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        // continue you business
    }

}
