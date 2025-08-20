package com.demo.encryptionanddecryption.utils.hdemo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES对称加密工具类（CBC模式 + 动态IV）
 * 安全说明：使用CBC模式避免ECB模式的安全风险，每次加密自动生成随机IV增强安全性
 */
public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;
    private static final int KEY_SIZE = 128;

    /**
     * 接口1：加密原始报文
     * @param secretKeyStr 密钥字符串（Base64编码）
     * @param plainText    原始报文字符串
     * @return Base64格式密文（含IV）
     */
    public static String encrypt(String secretKeyStr, String plainText) throws Exception {
        // 还原密钥
        SecretKey secretKey = parseKeyFromBase64(secretKeyStr);

        // 生成随机IV（每次加密唯一）
        byte[] ivBytes = new byte[IV_SIZE];
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        secureRandom.nextBytes(ivBytes);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 初始化加密器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // 加密并拼接IV+密文
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
        byte[] combined = new byte[ivBytes.length + cipherText.length];
        System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
        System.arraycopy(cipherText, 0, combined, ivBytes.length, cipherText.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * 接口2：解密密文
     * @param secretKeyStr  密钥字符串（Base64编码）
     * @param encryptedText 加密字符串（含IV）
     * @return 解密后的原始报文
     */
    public static String decrypt(String secretKeyStr, String encryptedText) throws Exception {
        // 还原密钥
        SecretKey secretKey = parseKeyFromBase64(secretKeyStr);
        byte[] combined = Base64.getDecoder().decode(encryptedText);

        // 分离IV和密文
        byte[] ivBytes = new byte[IV_SIZE];
        byte[] cipherText = new byte[combined.length - IV_SIZE];
        System.arraycopy(combined, 0, ivBytes, 0, IV_SIZE);
        System.arraycopy(combined, IV_SIZE, cipherText, 0, cipherText.length);

        // 初始化解密器
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        return new String(cipher.doFinal(cipherText), "UTF-8");
    }

    /**
     * 接口3：生成随机密钥字符串
     * @return Base64编码的密钥字符串
     */
    public static String generateKeyString() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE, SecureRandom.getInstanceStrong());
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 内部方法：从Base64字符串还原密钥
     */
    private static SecretKey parseKeyFromBase64(String keyBase64) {
        byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}
