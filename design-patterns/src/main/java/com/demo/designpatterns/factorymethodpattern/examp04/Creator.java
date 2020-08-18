package com.demo.designpatterns.factorymethodpattern.examp04;

/**
 * @ClassName Creator
 * @Description
 * @Author hup
 * @Date 2020/8/17 18:15
 * @Version 1.0
 */
public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}
