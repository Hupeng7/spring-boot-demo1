package com.demo.designpatterns.factorymethodpattern.examp02;

import com.demo.designpatterns.factorymethodpattern.examp01.Animal;

/**
 * @ClassName AnimalFactory
 * @Description
 * @Author hup
 * @Date 2020/8/13 18:46
 * @Version 1.0
 */
public class AnimalFactory {
    public static Dog createDog() {
        return new Dog();
    }

    public static Cat createCat() {
        return new Cat();
    }

    //
    public static Animal createAnimal(String type) {
        if ("dog".equals(type)) {
            return new Dog();
        } else if ("cat".equals(type)) {
            return new Cat();
        } else {
            return null;
        }

    }

}
