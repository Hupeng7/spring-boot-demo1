package com.demo.encryptionanddecryption.utils.hdemo;

import lombok.Data;

@Data
public class ApiResponse {
    private String respData;
    private String sign;
    private String respCode;
    private String respMsg;
    private String seqNo;
}
