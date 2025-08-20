package com.demo.encryptionanddecryption.utils.hdemo;

import lombok.Data;

@Data
public class ApiRequest {
    private String account;
    private String key;
    private String sign;
    private String reqData;
}
