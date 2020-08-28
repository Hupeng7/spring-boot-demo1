package com.demo.designpatterns.templatemethodpattern;

/**
 * @ClassName Client
 * @Description 模板方法模式  Template Method Pattern
 * 定义一个操作中的算法框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构
 * 即可重新定义该算法的某些特定步骤
 * 注意： 为了防止恶意的操作，一般模板方法都加上final关键字，不允许被覆写
 * 抽象模板中的基本方法尽量设计为protected类型，符合迪米特法则，不需要暴露的属性或
 * 方法尽量不要设置为protected类型。实现类若非必要，尽量不要扩大父类中的访问权限
 * 优点：1.封装不变部分，扩展可变部分
 * 2.提取公共部分代码，便于维护
 * 3.行为由父类控制，子类实现
 * 缺点：1.在复杂项目中，会带来代码阅读难度
 * @Author hup
 * @Date 2020/8/19 12:05
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        class1.templateMethod();
        class2.templateMethod();


    }

}
