package com.demo.designpatterns.factorymethodpattern.examp01;

// 工厂方法模式
// 动物工厂
public interface AnimalFactory {
    // 可以获取任何的宠物
    Animal createAnimal();
}
