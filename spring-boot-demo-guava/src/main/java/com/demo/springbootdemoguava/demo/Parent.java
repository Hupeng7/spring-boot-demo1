package com.demo.springbootdemoguava.demo;

import com.google.common.collect.ComparisonChain;
import com.google.common.reflect.TypeToken;

/**
 * @ClassName Parent
 * @Description TODO
 * @Author hup
 * @Date 2020/7/17 9:45
 * @Version 1.0
 */
public class Parent<T> implements Comparable<Parent> {
    TypeToken<T> type = new TypeToken<T>(getClass()) {
    };

    private int age;

    public Parent(int age) {
        this.age = age;
    }

    public void getGenericityType() {
        System.out.println("getGenericityType:type.getRawType():" + type.getRawType());
    }

    @Override
    public int compareTo(Parent o) {
        return ComparisonChain.start().compare(this.age, o.age).result();
    }
}
