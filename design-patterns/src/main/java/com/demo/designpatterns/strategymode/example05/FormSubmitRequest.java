package com.demo.designpatterns.strategymode.example05;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName FormSubmitRequest
 * @Description 表单提交的请求
 * @Author H
 * @Date 2021/1/29 17:47
 * @Version 1.0
 */
@Data
public class FormSubmitRequest {
    /**
     * 提交类型
     */
    private String submitType;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 表单提交的值
     */
    private Map<String, Object> formInput;
}
