package com.example.springbootdemomytool.beans;

import lombok.Data;

/**
 * @ClassName DataBean
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/22 16:08
 * @Version 1.0
 */
@Data
public class DataBean {
    private int noticeId;
    private String noticeTitle;
    private Object noticeImg;
    private long noticeCreateTime;
    private long noticeUpdateTime;
    private String noticeContent;

}
