package com.demo.exception.handler.exception;

import com.demo.exception.handler.constant.Status;

/**
 * @ClassName JsonException
 * @Description JSON异常
 * @Author Leo
 * @Date 2019/11/22 17:58
 * @Version 1.0
 */
public class JsonException extends BaseException {
    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
