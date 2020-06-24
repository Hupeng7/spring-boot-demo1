package com.example.springbootdemomytool.beans;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Notice
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/22 16:07
 * @Version 1.0
 */
@Data
public class Notice {
    private int status;
    private Object msg;
    private List<DataBean> data;

}
