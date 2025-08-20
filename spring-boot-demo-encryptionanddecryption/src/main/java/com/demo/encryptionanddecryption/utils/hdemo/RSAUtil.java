package com.demo.encryptionanddecryption.utils.hdemo;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA算法，实现数据的加密解密。
 */
@Slf4j
public class RSAUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    private static final String RSA = "RSA";

    private static final String SIGN_ALGORITHM = "SHA1WithRSA";


    /**
     * 生成密钥对
     *
     * @return
     */
    public static Map<String, String> generateKeyPair() {
        try {
            return generateKeyPair(2048);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    public static Map<String, String> generateKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
            // 密钥位数
            keyPairGen.initialize(keySize);
            // 密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 公钥
            PublicKey publicKey = keyPair.getPublic();
            // 私钥
            PrivateKey privateKey = keyPair.getPrivate();
            //得到公钥字符串
            String publicKeyString = getKeyString(publicKey);
            //得到私钥字符串
            String privateKeyString = getKeyString(privateKey);
            //将生成的密钥对返回
            Map<String, String> map = new HashMap<String, String>();
            map.put("publicKey", publicKeyString);
            map.put("privateKey", privateKeyString);
            return map;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 得到密钥字符串（经过base64编码）
     *
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    /**
     * 使用公钥对明文进行加密
     *
     * @param publicKey 公钥
     * @param plainText 明文
     * @return
     */
    public static String encrypt(String publicKey, String plainText) throws Exception {
        Cipher cipher= Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        byte[] decryptedData = plainText.getBytes("UTF-8");
        int inputLen = decryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(decryptedData, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(decryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);

    }

    /**
     * 使用私钥对密文进行解密
     *
     * @param privateKey 私钥
     * @param enStr      密文
     * @return
     */
    public static String decrypt(String privateKey, String enStr) throws Exception {
        Cipher cipher= Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        byte[] encryptedData = Base64.getDecoder().decode(enStr);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, "UTF-8");

    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data       已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        return sign(data, privateKey, SIGN_ALGORITHM);
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data               已加密数据
     * @param privateKey         私钥(BASE64编码)
     * @param signatureAlgorithm 签名算法, 如  MD5withRSA,SHA1withRSA
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey,
                              String signatureAlgorithm) throws Exception {
        PrivateKey privateK = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(signatureAlgorithm);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data      已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        return verify(data, publicKey, sign, SIGN_ALGORITHM);
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data               已加密数据
     * @param publicKey          公钥(BASE64编码)
     * @param sign               数字签名
     * @param signatureAlgorithm 数字签名算法  MD5withRSA, SHA1withRSA
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign,
                                 String signatureAlgorithm) throws Exception {
        PublicKey publicK = getPublicKey(publicKey);
        Signature signature = Signature.getInstance(signatureAlgorithm);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    public static String safeSign(String data, String privateKey) {
        try {
            return sign(data.getBytes(StandardCharsets.UTF_8), privateKey);
        } catch (Exception e) {
            log.error("sign error", e);
            return "";
        }
    }

    public static boolean safeVerifySign(String data, String publicKey, String sign) {
        try {
            return verify(data.getBytes(StandardCharsets.UTF_8), publicKey, sign);
        } catch (Exception e) {
            log.error("verify sign error", e);
            return false;
        }
    }

}
