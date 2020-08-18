package com.demo.designpatterns.factorymethodpattern.examp03;


/**
 * @ClassName AnimalFactory
 * @Description
 * @Author hup
 * @Date 2020/8/13 18:58
 * @Version 1.0
 */
public interface AnimalFactory {
    Animal createDog();

    Animal createCat();

}
