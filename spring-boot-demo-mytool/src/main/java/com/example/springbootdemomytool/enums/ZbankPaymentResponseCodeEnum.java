package com.example.springbootdemomytool.enums;

/**
 * 众邦 借款申请 返回值code
 */
public enum ZbankPaymentResponseCodeEnum {
    XD0001("XD0001"),
    XD0010("XD0010");

    private String code;

    ZbankPaymentResponseCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
