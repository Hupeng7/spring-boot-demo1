package com.demo.codegen.common;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 14:12
 * @Version 1.0
 */
@Getter
public enum ResultCode implements IResultCode {

    /**
     * 成功
     */
    OK(200, "成功"),

    /**
     * 失败
     */
    ERROR(500, "失败");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
