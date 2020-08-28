package com.demo.designpatterns.proxypattern.examp01;

/**
 * @ClassName Proxy
 * @Description
 * @Author hup
 * @Date 2020/8/19 18:22
 * @Version 1.0
 */
public class Proxy implements Subject {
    // 要代理哪个实现类
    private Subject subject = null;

    // 默认被代理者
    public Proxy() {
        this.subject = new RealSubject();
    }

//    // 通过构造函数传递代理者
//    public Proxy(Object... objects) {
//
//    }
    // 通过构造函数传递被代理者
    public Proxy(Subject _subject) {
        subject = _subject;
    }

    // 实现接口中的方法
    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    private void after() {
        System.out.println("do something after hahaha ...");
    }

    private void before() {
        System.out.println("do something before...");
    }
}
