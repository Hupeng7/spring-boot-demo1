package com.demo.encryptionanddecryption.utils.hdemo;

import java.util.HashMap;
import java.util.Map;

public enum HzBizCode {
    SUCCESS_0000("0000", "请求成功"), 

    ERROR_1001("1001", "匹配结果为空"), 
    ERROR_1002("1002", "请求参数格式错误"),
    ERROR_1003("1003", "请求参数缺失"), 
    ERROR_1004("1004", "系统异常"), 
    ERROR_1005("1005", "账号或密码错误"),
    ERROR_1006("1006", "无接口请求权限"), 
    ERROR_1007("1007", "连接超时，请重新请求"), 
    ERROR_1008("1008", "并发限制"),
    ERROR_1009("1009", "查询内容被限制"), 
    ERROR_1010("1010", "无效接口"), 
    ERROR_1011("1011", "卡片异常"),
    ERROR_1012("1012", "无法判断");

    private String code;
    private String desc;

    public static final Map<String, String> CODE_DESC_MAP = new HashMap<>(16,1.0f);
    private static final HzBizCode[] VALUES = values();

    static {
        for (HzBizCode hzBizCode : VALUES) {
            CODE_DESC_MAP.put(hzBizCode.code, hzBizCode.desc);
        }
    }

    HzBizCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(String code) {
        return CODE_DESC_MAP.getOrDefault(code, "");
    }
}