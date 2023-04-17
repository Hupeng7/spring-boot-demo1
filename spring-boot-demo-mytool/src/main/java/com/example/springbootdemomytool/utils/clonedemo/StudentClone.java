package com.example.springbootdemomytool.utils.clonedemo;

/**
 * @ClassName StudentClone
 * @Description 学生类实现 Cloneable 接口
 * @Author H
 * @Date 2023/3/9 16:00
 * @Version 1.0
 */
public class StudentClone implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() {
        StudentClone studentClone = null;
        try {
            studentClone = (StudentClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return studentClone;
    }
}
