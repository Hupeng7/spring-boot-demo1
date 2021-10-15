package com.demo.orm.postgresql;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName UserInfo
 * @Description
 * @Author H
 * @Date 2021/10/9 10:05
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    public UserInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
