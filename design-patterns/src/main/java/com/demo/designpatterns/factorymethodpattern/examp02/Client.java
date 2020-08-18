package com.demo.designpatterns.factorymethodpattern.examp02;

import com.demo.designpatterns.factorymethodpattern.examp01.Animal;

/**
 * @ClassName Client
 * @Description 简单/静态工厂模式
 * 优点：1.就一个具体工厂来创建对象，静态方法，代码量少，
 * 缺点：1.需求变了就要改代码，扩展性差
 * @Author hup
 * @Date 2020/8/13 18:53
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        Animal A = AnimalFactory.createAnimal("dog");
        A.eat();

        Animal C = AnimalFactory.createCat();
        C.eat();
    }

}
