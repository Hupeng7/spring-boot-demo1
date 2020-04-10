package com.example.springbootdemomytool.utils.reflectDemo;

import com.example.springbootdemomytool.beans.Book;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName ReflectClassDemo1
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/9 11:46
 * @Version 1.0
 */
public class ReflectClassDemo1 {

    public static void main(String[] args) {
//        getClassMethod();
//
//        forNameMethod();
//
//        getConstructors();
//
//        getConstructor();
//        getNewInstance();

        getPrivateMethod();


    }

    // 根据一个字符串得到一个类
    private static void getClassMethod() {
        String name = "ZhangSan";
        Class clazz = name.getClass();
        System.out.println(clazz.getName());
    }

    // Class.forName
    private static void forNameMethod() {
        String name = "java.lang.String";
        Class clazz = null;
        try {
            clazz = Class.forName(name);
            System.out.println(clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取类的所有构造方法
    private static void getConstructors() {
        Book book = new Book();
        Class clazz = book.getClass();
        Constructor[] constructors;
        constructors = clazz.getDeclaredConstructors();
        System.out.println("=================获取类的所有构造方法=================");
        for (int i = 0; i < constructors.length; i++) {
            System.out.println(Modifier.toString(constructors[i].getModifiers()) + "参数： ");
            Class[] parametertypes = constructors[i].getParameterTypes();
            for (int j = 0; j < parametertypes.length; j++) {
                System.out.println(parametertypes[j].getName() + " ");
            }
            System.out.println("");
        }
    }

    // 获取类中特定的构造方法
    public static void getConstructor() {
        Book book = new Book();
        Class clazz = book.getClass();
        try {
            System.out.println("=================获取类中特定的构造方法 一个参数=================");
            Constructor constructor;
            constructor = clazz.getDeclaredConstructor(String.class);
            System.out.println(Modifier.toString(constructor.getModifiers()));
            System.out.println(constructor.getParameterTypes()[0].getName().toString());

            System.out.println("=================获取类中特定的构造方法 两个参数=================");
            Class[] p = {String.class, String.class};
            Constructor constructors;
            constructors = clazz.getDeclaredConstructor(p);
            System.out.println(Modifier.toString(constructors.getModifiers()) + "参数: ");
            Class[] parametertypes = constructors.getParameterTypes();
            for (int i = 0; i < parametertypes.length; i++) {
                System.out.println(parametertypes[i].getName() + " ");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // 调用构造方法
    public static void getNewInstance() {
        Book book = new Book();
        Class clazz = book.getClass();

        Class[] p = {String.class, String.class};
        Constructor constructor;
        try {
            constructor = clazz.getDeclaredConstructor(p);
            constructor.newInstance("Book One", "LiSi");
            book.setName("Book One");
            System.out.println(book.toString()); // Book{name='Book One', author='null'}

            // 那么调用私有构造方法呢，和上面一样，只是我们要设置constructors.setAccessible(true);代码如下
            constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            constructor.newInstance("Book Two");
            System.out.println(constructor.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 调用类的私有方法
    public static void getPrivateMethod() {
        Book book = new Book();
        Class clazz = book.getClass();
        Class[] p = {int.class};
        try {
            Method method = clazz.getDeclaredMethod("declaredMethod", p);
            method.setAccessible(true);

            Object bookObject = clazz.newInstance();
            String string = (String) method.invoke(bookObject, 1);
            System.out.println(string);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}
