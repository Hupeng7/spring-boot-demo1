package com.example.springbootdemomytool.utils.java8;

import lombok.Data;

/**
 * @ClassName Person
 * @Description
 * @Author H
 * @Date 2022/7/22 10:14
 * @Version 1.0
 */
@Data
public class Person {
    private String name;
    private int salary;
    private int age;
    private String sex;
    private String area;

    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }
}
