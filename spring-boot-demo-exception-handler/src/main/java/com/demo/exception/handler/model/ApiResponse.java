package com.demo.exception.handler.model;

import com.demo.exception.handler.constant.Status;
import com.demo.exception.handler.exception.BaseException;
import lombok.Data;

/**
 * @ClassName ApiResponse
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/22 17:26
 * @Version 1.0
 */
@Data
public class ApiResponse {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回内容
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 无参构造函数
     */
    public ApiResponse() {
    }

    /**
     * 全参构造函数
     *
     * @param code
     * @param message
     * @param data
     */
    public ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data
     * @return
     */
    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(Status.OK, data);
    }

    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message
     * @return
     */
    public static ApiResponse ofMessage(String message) {
        return of(Status.OK.getCode(), message, null);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status
     * @return
     */
    public static ApiResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status
     * @param data
     * @return
     */
    public static ApiResponse ofStatus(Status status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t
     * @param data
     * @param <T>
     * @return
     */
    public static <T extends BaseException> ApiResponse ofException(T t, Object data) {
        return of(t.getCode(), t.getMessage(), data);
    }

    /**
     * 构造一个异常且不带数据的API返回
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends BaseException> ApiResponse ofException(T t) {
        return ofException(t, null);
    }

}
