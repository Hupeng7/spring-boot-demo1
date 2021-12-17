package com.example.springbootdemomytool.utils.completablefuturedemo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName UserInfo
 * @Description
 * @Author H
 * @Date 2021/12/15 11:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class UserInfo {
    private String id;
    private String name;
    private Integer age;

}
