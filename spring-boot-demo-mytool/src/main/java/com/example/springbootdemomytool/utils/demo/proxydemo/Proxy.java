package com.example.springbootdemomytool.utils.demo.proxydemo;

/**
 * @ClassName Proxy
 * @Description 代理主题角色
 * @Author hup
 * @Date 2020/8/18 17:46
 * @Version 1.0
 */
public class Proxy implements Subject {
    // 要代理哪个实现类
    private Subject subject = null;

    // 默认被代理者
    public Proxy() {
        subject = new RealSubject();
    }

    // 通过构造函数传递被代理者
    public Proxy(Subject _subject) {
        subject = _subject;
    }

    // 实现接口中的定义的方法
    @Override
    public void request() {
        before();
        subject.request();
        after();
    }

    private void after() {
        System.out.println("do something after...");
    }

    private void before() {
        System.out.println("do something before...");
    }
}
