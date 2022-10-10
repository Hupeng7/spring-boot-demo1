package com.example.springbootdemomytool.utils.mapstructdemo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Person
 * @Description
 * @Author H
 * @Date 2022/10/10 14:04
 * @Version 1.0
 */
@Data
public class Person {
    String describe;

    private String id;
    private String name;
    private int age;
    private BigDecimal source;
    private double height;
    private Date createTime;

}
