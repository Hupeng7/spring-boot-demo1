package com.example.springbootdemomytool.utils.encrypanddecrypdemo;

import java.security.MessageDigest;

/**
 * @ClassName EncryptDemo
 * @Description
 * @Author H
 * @Date 2021/2/4 10:24
 * @Version 1.0
 */
public class EncryptDemo {
    private final static String KEY_MD5 = "md5";

    public static void main(String[] args) {



    }

    public static byte[] encrytMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

}
