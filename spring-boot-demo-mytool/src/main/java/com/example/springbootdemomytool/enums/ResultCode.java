package com.example.springbootdemomytool.enums;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/13 18:22
 * @Version 1.0
 */
@Getter
public enum ResultCode {
    SUCCESS(10000, "success"),

    FAILED(10001, "响应失败"),

    VALIDATE_FAILED(10002, "参数校验失败"),

    ERROR(50000, "未知错误");

    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
