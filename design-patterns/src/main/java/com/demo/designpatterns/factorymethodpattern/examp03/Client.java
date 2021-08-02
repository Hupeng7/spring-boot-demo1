package com.demo.designpatterns.factorymethodpattern.examp03;

/**
 * @ClassName Client
 * @Description 抽象工厂
 * @Author H
 * @Date 2021/7/7 10:42
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        //
        AnimalFactory factory = new FemaleAnimalFactory();
        Cat cat = (Cat) factory.createCat();
        cat.eat();

        Dog dog = (Dog) factory.createDog();
        dog.eat();


    }
}
