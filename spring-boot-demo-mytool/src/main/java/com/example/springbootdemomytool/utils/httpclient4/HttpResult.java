package com.example.springbootdemomytool.utils.httpclient4;

/**
 * @ClassName gadf
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/27 17:39
 * @Version 1.0
 */
public class HttpResult {

    // 响应码
    private Integer code;

    // 响应体
    private String body;


    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, String body) {
        super();
        this.code = code;
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }
}