package com.demo.designpatterns.templatemethodpattern;

/**
 * @ClassName AbstractClass
 * @Description
 * @Author hup
 * @Date 2020/8/19 11:59
 * @Version 1.0
 */
public abstract class AbstractClass {
    // 基本方法
    protected abstract void doSomething();

    // 基本方法
    protected abstract void doAnything();

    // 模板方法
    public void templateMethod() {
        /**
         * 调用基本方法，完成相关的逻辑
         */
        this.doSomething();
        this.doSomething();
    }

}
