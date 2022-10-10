package com.example.springbootdemomytool.utils.mapstructdemo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName PersonDTO
 * @Description
 * @Author H
 * @Date 2022/10/10 14:05
 * @Version 1.0
 */
@Data
public class PersonDTO {
    String describe;
    private Long id;
    private String personName;
    private String age;
    private String source;
    private String height;
    private String createTime;
    private Date updateTime;

}
