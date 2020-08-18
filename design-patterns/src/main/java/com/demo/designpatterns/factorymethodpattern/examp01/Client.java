package com.demo.designpatterns.factorymethodpattern.examp01;

/**
 * @ClassName Client
 * @Description 工厂方法模式
 * 优点： 1.客户端不需要在负责对象的创建，明确了各个类的职责
 * 2.如果有新的对象增加，只需要增加一个具体的类和具体的工厂类即可
 * 3.不会影响已有的代码，后期维护容易，增强系统的扩展性
 *
 * 缺点：1.需要额外的编写代码，增加工作量
 * @Author hup
 * @Date 2020/8/13 18:01
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        AnimalFactory f = new DogFactory();
        Animal animal = f.createAnimal();
        animal.eat();

        AnimalFactory ff = new CatFactory();
        Animal animal1 = ff.createAnimal();
        animal1.eat();

    }
}
