package com.demo.encryptionanddecryption.utils.md5util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * @ClassName MD5Util
 * @Description
 * @Author H
 * @Date 2025/6/11 14:32
 * @Version 1.0
 */
@Slf4j
public class MD5Util {
    // 盐长度
    private static final int SALT_LENGTH = 32;
    // 完整密码长度 （盐值 + MD5哈希）
    private static final int FULL_PASSWORD_LENGTH = 64;

    private MD5Util() {
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
        return salt + hash;
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
        String salt = storedPassword.substring(0, SALT_LENGTH);

        String inputHash = calculateHash(inputPassword, salt);

        return (salt + inputHash).equals(storedPassword);
    }

    public static void main(String[] args) {
        String source = "123456";
        String hashedPassword = generateWithSalt(source);
        log.info("hashedPassword:{}", hashedPassword);

        Boolean verify = verify(hashedPassword, source);
        log.info("verify:{}", verify);

        Boolean isWrongValid = verify(hashedPassword, "wrongPassword");
        log.info("wrong password result: {}", isWrongValid);
    }


}
