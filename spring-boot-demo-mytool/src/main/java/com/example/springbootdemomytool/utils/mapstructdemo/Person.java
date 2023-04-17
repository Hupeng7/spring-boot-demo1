package com.example.springbootdemomytool.utils.mapstructdemo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Person
 * @Description
 * @Author H
 * @Date 2022/10/10 14:04
 * @Version 1.0
 */
//@Data
public class Person {
    String describe;

    private String id;
    private String name;
    private int age;
    private BigDecimal source;
    private double height;
    private Date createTime;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSource() {
        return source;
    }

    public void setSource(BigDecimal source) {
        this.source = source;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
