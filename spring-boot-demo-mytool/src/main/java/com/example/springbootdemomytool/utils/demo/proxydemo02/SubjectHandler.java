package com.example.springbootdemomytool.utils.demo.proxydemo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName SubjectHandler
 * @Description TODO
 * @Author hup
 * @Date 2020/8/18 17:56
 * @Version 1.0
 */
public class SubjectHandler implements InvocationHandler {
    // 被代理的对象
    private Subject subject;

    public SubjectHandler(Subject _subject) {
        subject = _subject;
    }

    // 委托处理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something before...");
        Object object = method.invoke(subject, args);
        System.out.println("do something after...");
        return object;
    }
}
