package com.example.springbootdemomytool.utils.demo.reflectdecoratordemo;

/**
 * @ClassName Client
 * @Description 装饰模式 较通用
 * @Author hup
 * @Date 2020/8/18 18:34
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        //
        Animal rat = new Rat();
        rat = new DecorateAnimal(rat, FlyFeature.class);
        rat = new DecorateAnimal(rat, DigFeature.class);
        rat.doStuff();
    }

}
