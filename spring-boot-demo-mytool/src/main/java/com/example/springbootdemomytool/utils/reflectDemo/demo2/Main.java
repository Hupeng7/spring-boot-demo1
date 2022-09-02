package com.example.springbootdemomytool.utils.reflectDemo.demo2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName Main
 * @Description
 * @Author H
 * @Date 2022/8/17 17:38
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        /**
         * 获取 TargetClass 类的Class对象并且创建TargetObject 类实例
         */
        Class<?> targetClass = Class.forName("com.example.springbootdemomytool.utils.reflectDemo.demo2.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();

        /**
         * 获取 TargetObject 类中定义的所有方法
         */
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        /**
         * 获取指定方法并调用
         */
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "Java code");

        /**
         * 获取指定参数并对参数进行修改
         */
        Field field = targetClass.getDeclaredField("value");
        // 为了对类中的参数进行修改 我们去掉安全检查
        field.setAccessible(true);
        field.set(targetObject, "Java code1");

        /**
         * 调用private方法
         */
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        // 为了调用private方法 我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }

}
