package com.example.springbootdemomytool.utils.jsondemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Person
 * @Description
 * @Author H
 * @Date 2020/11/20 15:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private Integer id;
    private String fullName;
}
