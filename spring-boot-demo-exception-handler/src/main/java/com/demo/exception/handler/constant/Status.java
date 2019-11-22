package com.demo.exception.handler.constant;

import lombok.Getter;

/**
 * @ClassName Status
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/22 17:18
 * @Version 1.0
 */
@Getter
public enum Status {
    /**
     * 操作成功
     */
    OK(200, "操作成功"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR(500, "服务器出错啦");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
