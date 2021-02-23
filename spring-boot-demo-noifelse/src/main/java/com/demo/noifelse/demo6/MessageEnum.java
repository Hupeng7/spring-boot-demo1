package com.demo.noifelse.demo6;

import java.util.Arrays;

/**
 * @ClassName MessageEnum
 * @Description
 * @Author H
 * @Date 2021/2/9 14:42
 * @Version 1.0
 */
public enum MessageEnum {
    SUCCESS(1, "成功"),
    FAIL(-1, "失败"),
    TIME_OUT(-2, "网络超时"),
    PARAM_ERROR(-3, "参数错误");

    private int code;
    private String message;

    MessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int code){
        MessageEnum messageEnum = MessageEnum.getMessageEnum(code);
        return messageEnum.getMessage();
    }

    public static MessageEnum getMessageEnum(int code) {
        return Arrays.stream(MessageEnum.values()).filter(x -> x.code == code).findFirst().orElse(null);
    }

    public static MessageEnum getMessageEnum1(int code) {
        for (MessageEnum messageEnum : MessageEnum.values()) {
            if (code == messageEnum.code) {
                return messageEnum;
            }
        }
        return null;
    }

}
