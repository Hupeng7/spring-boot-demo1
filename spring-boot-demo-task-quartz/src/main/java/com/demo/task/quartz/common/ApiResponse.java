package com.demo.task.quartz.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @ClassName ApiResponse
 * @Description 通用API 返回值封装
 * @Author Leo
 * @Date 2019/11/2 11:02
 * @Version 1.0
 */
@Data
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 8686999291668969796L;
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public ApiResponse() {
    }

    /**
     * 通用封装获取 ApiResponse对象
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
     * 通用封装获取ApiResponse对象
     *
     * @param code
     * @param message
     * @param data
     * @return ApiResponse
     */
    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    /**
     * 通用成功封装获取ApiResponse对象
     *
     * @param data
     * @return ApiResponse
     */
    public static ApiResponse ok(Object data) {
        return new ApiResponse(200, HttpStatus.OK.getReasonPhrase(), data);
    }

    /**
     * 通用封装获取ApuResponse对象
     *
     * @param message
     * @return ApiResponse
     */
    public static ApiResponse msg(String message) {
        return of(200, message, null);
    }

    /**
     * 通用失败封装获取ApiResponse对象
     *
     * @param message
     * @return ApiResponse
     */
    public static ApiResponse error(String message) {
        return of(500, message, null);
    }
}
