package com.example.springbootdemomytool.utils.idcreate;

import java.util.UUID;

/**
 * @ClassName UUIDGenerator
 * @Description
 * @Author H
 * @Date 2021/4/15 18:14
 * @Version 1.0
 */
public class UUIDGenerator {

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
