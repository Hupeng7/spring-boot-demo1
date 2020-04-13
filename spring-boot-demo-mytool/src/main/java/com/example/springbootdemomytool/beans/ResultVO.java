package com.example.springbootdemomytool.beans;

import com.example.springbootdemomytool.enums.ResultCode;
import lombok.Getter;

/**
 * @ClassName ResultVO
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/13 17:42
 * @Version 1.0
 */
@Getter
public class ResultVO<T> {

    private int code;

    private String msg;

    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }
}
