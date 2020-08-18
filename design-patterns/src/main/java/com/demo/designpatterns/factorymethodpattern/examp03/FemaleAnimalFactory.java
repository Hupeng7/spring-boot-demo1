package com.demo.designpatterns.factorymethodpattern.examp03;


/**
 * @ClassName FemaleAnimalFactory
 * @Description
 * @Author hup
 * @Date 2020/8/13 19:02
 * @Version 1.0
 */
public class FemaleAnimalFactory implements AnimalFactory{
    @Override
    public Animal createDog() {
        return null;
    }

    @Override
    public Animal createCat() {
        return null;
    }
}
