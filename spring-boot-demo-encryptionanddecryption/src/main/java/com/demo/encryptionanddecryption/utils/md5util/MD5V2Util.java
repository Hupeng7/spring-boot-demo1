package com.demo.encryptionanddecryption.utils.md5util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * @ClassName MD5Util
 * @Description 取32位盐值中间位置，将加盐后的 password 插入，形成一个新的64位的密文
 * @Author H
 * @Date 2025/6/11 14:32
 * @Version 1.0
 */
@Slf4j
public class MD5V2Util {
    // 盐长度
    private static final int SALT_LENGTH = 32;
    // 完整密码长度 （盐值 + MD5哈希）
    private static final int FULL_PASSWORD_LENGTH = 64;
    // 随机插入的位置角标
    private static final int INSERT_INDEX = 11;
    private static final int PASSWORD_LENGTH = 32;

    private MD5V2Util() {
        // 防止实例化
    }

    // 生成随机盐值
    private static String generateSalt() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String calculateHash(String source, String salt) {
        String combined = source + salt;
        return DigestUtils.md5Hex(combined.getBytes(StandardCharsets.UTF_8));
    }

    private static Boolean isValidInput(String storedPassword, String inputPassword) {
        if (!StringUtils.hasLength(storedPassword) || !StringUtils.hasLength(inputPassword)) {
            log.warn("storedPassword or inputPassword is empty!");
            return false;
        }

        if (storedPassword.length() != FULL_PASSWORD_LENGTH) {
            log.warn("invalid storedPassword length: {}", storedPassword.length());
            return false;
        }
        return true;
    }

    /**
     * 生成加盐的 MD5 密文
     *
     * @return 盐值（32位） + MD5哈希（32位） = 64位字符串
     */
    public static String generateWithSalt(String source) {
        if (!StringUtils.hasLength(source)) {
            throw new IllegalArgumentException("source password cannot be null or empty");
        }
        // 加盐
        String salt = generateSalt();
        log.debug("salt:{}", salt);

        // 使用 MD5 进行加密 明文 + 盐值
        String hash = calculateHash(source, salt);

        // 存储到 SQL 中的是 盐值 + 密文
        log.debug("generated hash:{}", hash);
        return combinedSourceString(salt, hash, INSERT_INDEX);
    }

    /**
     * MD5 验证
     */
    public static Boolean verify(String storedPassword, String inputPassword) {
        // 校验一下数据
        if (!isValidInput(storedPassword, inputPassword)) {
            return false;
        }

        // 提取盐值
        String salt = getSaltString(storedPassword, INSERT_INDEX);

        String inputHash = calculateHash(inputPassword, salt);

        String combined = combinedSourceString(salt, inputHash, INSERT_INDEX);

        return combined.equals(storedPassword);
    }

    public static void main(String[] args) {
        String source = "123456";
        String hashedPassword = generateWithSalt(source);
        log.info("可以存入数据库的密码  hashedPassword:{}", hashedPassword);

        Boolean verify = verify(hashedPassword, source);
        log.info("verify:{}", verify);

        Boolean isWrongValid = verify(hashedPassword, "wrongPassword");
        log.info("wrong password result: {}", isWrongValid);

        test1();
    }

    public static void test1() {
        String a = "12345678901234567890123456789012"; // 32 characters
        String b = "abcdefghijklmnopqrstuvwxyzabcdef"; // 32 characters
        int n = 5; // 插入的位置（0-based index）

        // 使用StringBuilder插入
        StringBuilder sb = new StringBuilder(a);
        sb.insert(n, b);
        String result = sb.toString();

        System.out.println("Result: " + result);
        System.out.println("Length: " + result.length()); // 应该是 32 + 32 = 64
    }

    /**
     * source 插入 salt的index 位置生成一个新的密文 storedPassword
     */
    public static String combinedSourceString(String salt, String source, int index) {
        StringBuilder sb = new StringBuilder(salt);
        sb.insert(index, source);
        return sb.toString();
    }

    /**
     * 从 storedPassword 取密文
     */
    public static String getSourceString(String storedPassword, int index) {
        return storedPassword.substring(index, index + PASSWORD_LENGTH);
    }

    /**
     * 从 storedPassword 取盐值
     */
    public static String getSaltString(String storedPassword, int index) {
        return storedPassword.substring(0, index) + storedPassword.substring(index + PASSWORD_LENGTH);
    }


}
