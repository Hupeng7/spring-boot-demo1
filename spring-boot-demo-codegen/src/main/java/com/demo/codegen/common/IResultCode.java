package com.demo.codegen.common;

/**
 * @ClassName IResultCode
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 11:51
 * @Version 1.0
 */
public interface IResultCode {

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 获取返回消息
     *
     * @return 返回消息
     */
    String getMessage();


}
