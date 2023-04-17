package com.example.springbootdemomytool.utils.mapstructdemo;

import java.util.Date;

/**
 * @ClassName PersonDTO
 * @Description
 * @Author H
 * @Date 2022/10/10 14:05
 * @Version 1.0
 */
//@Data
public class PersonDTO {
    String describe;
    private Long id;
    private String personName;
    private String age;
    private String source;
    private String height;
    private String createTime;
    private Date updateTime;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
