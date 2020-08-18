package com.demo.designpatterns.factorymethodpattern.examp01;

/**
 * @ClassName DogFactory
 * @Description
 * @Author hup
 * @Date 2020/8/13 18:00
 * @Version 1.0
 */
public class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}
