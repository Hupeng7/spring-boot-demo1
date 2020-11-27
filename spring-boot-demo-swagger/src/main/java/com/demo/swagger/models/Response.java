package com.demo.swagger.models;

/**
 * @ClassName Response
 * @Description
 * @Author H
 * @Date 2020/11/18 15:22
 * @Version 1.0
 */
public class Response<T> {
    private int resCode;
    private String errorMsg;
    private T data;

    public Response() {
        this.resCode = ResCodeMessage.SUCCESS;
        this.errorMsg = ResCodeMessage.getMessage(resCode);
    }

    public Response(int resCode) {
        this.resCode = resCode;
    }

    public Response(int resCode, String errorMsg) {
        this.resCode = resCode;
        this.errorMsg = errorMsg;
    }

    public Response(int resCode, String errorMsg, T data) {
        this.resCode = resCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response<T> data(T data) {
        this.data = data;
        return this;
    }

}
