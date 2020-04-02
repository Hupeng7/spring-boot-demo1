package com.example.springbootdemomytool;

/**
 * @ClassName ClassForNameTest
 * @Description
 * https://www.jianshu.com/p/5668d3059d64
 * @Author Leo
 * @Date 2020/4/1 17:15
 * @Version 1.0
 */
public class ClassForNameTest {

    public static void main(String[] args) {
        // PersonOne 路径
        String personOne = "com.example.springbootdemomytool.beans.PersonOne";
        test(personOne);
    }

    private static void test(String personOne) {
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            System.out.println("ClassLoader testing...");
            Class<?> loaderPersonOne = loader.loadClass(personOne);
            System.out.println("personOne " + loaderPersonOne.getName());
            System.out.println("========================================");
            Class forNamePersonOne = Class.forName(personOne);
            System.out.println("Class.forName testing...");
            System.out.println("user " + forNamePersonOne.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
