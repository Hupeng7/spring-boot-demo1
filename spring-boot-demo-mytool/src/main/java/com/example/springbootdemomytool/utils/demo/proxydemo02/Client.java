package com.example.springbootdemomytool.utils.demo.proxydemo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName Client
 * @Description
 * @Author hup
 * @Date 2020/8/18 17:59
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        // 具体主题角色，也就是被代理类
        Subject subject = new RealSubject();
        // 代理实例的处理 Handler
        InvocationHandler handler = new SubjectHandler(subject);
        // 当前加载器
        ClassLoader classLoader = subject.getClass().getClassLoader();

        Subject proxy = (Subject) Proxy.newProxyInstance(classLoader, subject.getClass().getInterfaces(), handler);

        // 执行具体主题角色方法
        proxy.request();

    }


}
