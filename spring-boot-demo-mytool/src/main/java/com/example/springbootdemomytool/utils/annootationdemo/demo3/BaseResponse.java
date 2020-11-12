package com.example.springbootdemomytool.utils.annootationdemo.demo3;

import lombok.Data;

/**
 * @ClassName BaseResponse
 * @Description
 * @Author hup
 * @Date 2020/11/9 15:54
 * @Version 1.0
 */
@Data
public class BaseResponse {
    private boolean success;

    private String responseMessage;

    private String responseCode;

}
