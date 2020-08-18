package com.demo.designpatterns.factorymethodpattern.examp01;

/**
 * @ClassName CatFactory
 * @Description 
 * @Author hup
 * @Date 2020/8/13 17:59
 * @Version 1.0
 */
public class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
