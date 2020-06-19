package com.example.springbootdemomytool.beans;

import java.io.Serializable;

/**
 * @ClassName User1
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/19 14:39
 * @Version 1.0
 */
public class User1 implements Serializable {
    private static final long serialVersionUID = 4308126651389279056L;

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
