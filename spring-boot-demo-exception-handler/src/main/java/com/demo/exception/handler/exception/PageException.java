package com.demo.exception.handler.exception;

import com.demo.exception.handler.constant.Status;

/**
 * @ClassName PageException
 * @Description 页面异常
 * @Author Leo
 * @Date 2019/11/22 17:59
 * @Version 1.0
 */
public class PageException extends BaseException {
    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }
}
