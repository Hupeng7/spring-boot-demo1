package com.example.springbootdemomytool.utils.clonedemo;

/**
 * @ClassName Test
 * @Description
 * @Author H
 * @Date 2023/3/9 16:02
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        /**
         * 学生 1:test2
         * 学生 2:test2
         */
        test1();

        /**
         * 学生 1:test1
         * 学生 2:test2
         */
        test2();

    }

    public static void test1() {
        Student student1 = new Student();
        student1.setName("test1");

        Student student2 = student1;
        student2.setName("test2");

        System.out.println("学生 1:" + student1.getName());
        System.out.println("学生 2:" + student2.getName());
    }

    public static void test2() {
        StudentClone studentClone1 = new StudentClone();
        studentClone1.setName("test1");

        // 通过克隆创建学生 2
        StudentClone studentClone2 = new StudentClone();
        studentClone2.setName("test2");

        System.out.println("学生 1:" + studentClone1.getName());
        System.out.println("学生 2:" + studentClone2.getName());
    }
}
