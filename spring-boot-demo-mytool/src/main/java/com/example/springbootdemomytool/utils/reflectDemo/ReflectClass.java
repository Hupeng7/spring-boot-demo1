package com.example.springbootdemomytool.utils.reflectDemo;

import com.example.springbootdemomytool.beans.Book;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectClass
 * @Description
 * https://www.jianshu.com/p/9be58ee20dee
 * @Author Leo
 * @Date 2020/4/8 19:02
 * @Version 1.0
 */

@Slf4j
public class ReflectClass {

    private final static String TAG = "com.example.springbootdemomytool.utils.reflectDemo.ReflectClass";

    // 创建对象
    public static void reflectNewInstance() {
        try {
            Class<?> classBook = Class.forName("com.example.springbootdemomytool.beans.Book");
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("Spring Boot Thinking");
            book.setAuthor("zhangsan");
            System.out.println(TAG + "reflectNewInstance book = " + book.toString());
            log.info(TAG, "reflectNewInstance book = " + book.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 反射私有的构造方法
    public static void reflectPrivateConstructor() {
        try {
            Class classBook = Class.forName("com.example.springbootdemomytool.beans.Book");
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class, String.class);
            // 强制修改
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("book name one", "lisi");
            Book book = (Book) objectBook;
            System.out.println(TAG + "reflectPrivateConstructor book = " + book.toString());
            log.info(TAG, "reflectPrivateConstructor book = " + book.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 反射私有属性
    public static void reflectPrivateField() {
        try {
            Class<?> classBook = Class.forName("com.example.springbootdemomytool.beans.Book");
            Object objectBook = classBook.newInstance();
            Field fieldTag = classBook.getDeclaredField("TAG");
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
            System.out.println(TAG + "reflectPrivateField book = " + tag);
            log.info(TAG, "reflectPrivateField tag = " + tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 反射私有方法
    public static void reflectPrivateMethod() {
        try {
            Class<?> classBook = Class.forName("com.example.springbootdemomytool.beans.Book");
            Method methodBook = classBook.getDeclaredMethod("declaredMethod", int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook, 0);
            System.out.println(TAG + "reflectPrivateField book = " + string);
            log.info(TAG, "reflectPrivateMethod string = " + string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
