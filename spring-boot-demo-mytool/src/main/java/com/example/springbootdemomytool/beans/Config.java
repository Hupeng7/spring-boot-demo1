package com.example.springbootdemomytool.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Config
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:47
 * @Version 1.0
 */
@Data
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name, description, value;

    private long id;

    /**
     * 创建者，demo使用字符串，实际上应该用对象id
     */
    private String creator;
}