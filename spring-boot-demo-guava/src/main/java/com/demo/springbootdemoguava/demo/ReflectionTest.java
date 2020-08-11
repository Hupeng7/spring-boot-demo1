package com.demo.springbootdemoguava.demo;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

import java.io.Serializable;

/**
 * @ClassName ReflectionTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 18:54
 * @Version 1.0
 */
public class ReflectionTest extends Parent<String> implements Serializable {
    public ReflectionTest(int age) {
        super(age);
    }

    public static void main(String[] args) throws Exception {
        ReflectionTest reflectionTest = new ReflectionTest(1);
        reflectionTest.typeTokenTest();
        reflectionTest.invokableTest();
        reflectionTest.proxyTest();
    }

    private void proxyTest() {
        System.out.println("--------------------------------proxyTest--------------------------------");
        // 用这种语法产生jdk代理
        Reflection.newProxy(Serializable.class, ((proxy, method, args) -> null));
        System.out.println("用这种语法产生jdk代理：Reflection.newProxy(interface.class,invocationHandler)");
    }

    private void invokableTest() throws NoSuchMethodException {
        System.out.println("--------------------------------invokableTest,对Method级别的封装--------------------");
        // 提供了Invokable对象可以更简便的获取反射信息
        Invokable invokable = Invokable.from(ReflectionTest.class.getDeclaredMethod("customMethod", Integer.class));
        System.out.println("invokable.getDeclaringClass(): " + invokable.getDeclaringClass());

        System.out.println("invokable.getParameters(): " + invokable.getParameters());
        System.out.println("invokable.getReturnType(): " + invokable.getReturnType());
        System.out.println("invokable.isOverridable(): " + invokable.isOverridable());
        System.out.println("invokable.isAccessible(): " + invokable.isAccessible());
        System.out.println("invokable.getName(): " + invokable.getName());
        System.out.println("invokable.getAnnotatedReturnType(): " + invokable.getAnnotatedReturnType());

        System.out.println("invokable.getOwnerType(): " + invokable.getOwnerType());
        System.out.println("invokable.toString(): " + invokable.toString());

    }

    private void typeTokenTest() {
        System.out.println("typeToken,对class做一些封装，支持泛型-------------------------");
        // 通过这种写法可以获取泛型的具体类型
        getGenericityType();
        // 产生一个TypeToken对象
        TypeToken<ReflectionTest> typeToken = TypeToken.of(ReflectionTest.class);

        // 常用查询
        System.out.println("获取当前类和所有继承类:typeToken.getTypes().classes(): " + typeToken.getTypes().classes());
        System.out.println("获取所有实现的接口，包括继承的类中的实现接口:typeToken.getTypes().interfaces(): " + typeToken.getTypes().interfaces());
        System.out.println("classes+interfaces很常用这个api:typeToken.getTypes().rawTypes(): " + typeToken.getTypes().rawTypes());
        System.out.println("只是获取当前typeToken的持有类type类型:typeToken.getType: " + typeToken.getType());
        System.out.println("只是获取当前TypeToken的持有类的class类型：typeToken.getRawType: " + typeToken.getRawType());

    }

    private String customMethod(Integer no) {
        return String.valueOf(no);
    }

}
