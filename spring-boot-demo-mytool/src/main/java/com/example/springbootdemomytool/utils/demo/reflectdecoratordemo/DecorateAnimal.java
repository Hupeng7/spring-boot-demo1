package com.example.springbootdemomytool.utils.demo.reflectdecoratordemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * @ClassName DecorateAnimal
 * @Description 装饰类
 * @Author hup
 * @Date 2020/8/18 18:26
 * @Version 1.0
 */
public class DecorateAnimal implements Animal {
    // 被包装的动物
    private Animal animal;

    // 使用哪一个包装器
    private Class<? extends Feature> clz;

    public DecorateAnimal(Animal _animal, Class<? extends Feature> _clz) {
        animal = _animal;
        clz = _clz;
    }


    @Override
    public void doStuff() {
        InvocationHandler handler = new InvocationHandler() {
            // 具体包装行为
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object object = null;
                // 设置包装条件
                if (Modifier.isPublic(method.getModifiers())) {
                    object = method.invoke(clz.newInstance(), args);
                }
                animal.doStuff();
                return object;
            }
        };
        // 当前加载器
        ClassLoader classLoader = getClass().getClassLoader();
        // 动态代理，由Handler决定如何包装
        Feature proxy = (Feature) Proxy.newProxyInstance(classLoader, clz.getInterfaces(), handler);
        proxy.load();
    }
}
