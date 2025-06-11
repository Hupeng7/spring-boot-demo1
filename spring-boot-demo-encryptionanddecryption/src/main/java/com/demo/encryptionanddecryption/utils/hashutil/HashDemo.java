package com.demo.encryptionanddecryption.utils.hashutil;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName HashDemo
 * @Description
 * @Author H
 * @Date 2025/6/11 15:56
 * @Version 1.0
 */
public class HashDemo {

    // MD5 哈希
    public static String md5Hash(String input) {
        return DigestUtils.md5Hex(input);
    }

    // SHA-256 哈希
    public static String sha256Hash(String input) {
        return DigestUtils.sha256Hex(input);
    }

    // SHA-256 加盐哈希
    public static String sha256WithSalt(String input, String salt) {
        String combined = input + salt;
        return DigestUtils.sha256Hex(combined);
    }

    public static void main(String[] args) {
        String password = "mySecurePassword123";
        String salt = "randomSaltValue";

        System.out.println("MD5 Hash: " + md5Hash(password));
        System.out.println("SHA-256 Hash: " + sha256Hash(password));
        System.out.println("SHA-256 with Salt: " + sha256WithSalt(password, salt));
    }
}
