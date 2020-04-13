package com.example.springbootdemomytool.utils.apidemo.exceptions;

import lombok.Getter;

/**
 * @ClassName APIException
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/13 17:35
 * @Version 1.0
 */
@Getter
public class APIException extends RuntimeException {

    private int code;
    private String msg;

    public APIException() {
        this(10001, "接口错误");
    }

    public APIException(String msg) {
        this(10001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
