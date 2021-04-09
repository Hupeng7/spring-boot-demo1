package com.example.springbootdemomytool.utils.testdemo;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA算法，实现数据的加密解密。
 *
 */
public class RSAUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * 密钥位数
     */
    private static final int KEY_SIZE = 2048;


    /**
     * 生成密钥对
     *
     * @return
     */
    public static Map<String, String> generateKeyPair() {
        try {
            return generateKeyPair(KEY_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> generateKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
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
            e.printStackTrace();
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
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
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
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
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
        Cipher cipher = Cipher.getInstance("RSA");
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
        Cipher cipher = Cipher.getInstance("RSA");
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
        return sign(data, privateKey, "SHA1WithRSA");
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
        return verify(data, publicKey, sign, "SHA1WithRSA");
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

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 恒普的密钥对 -- 对接时恒普提供给平台方公钥hzphPublickKey
        Map<String, String> hzphKeyPair = generateKeyPair();
        //String hzphPublickKey = hzphKeyPair.get("publicKey");
        String hzphPublickKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuVIZ0Zeoo9v85R7Eg467fSUj0pYGDFgWq/b+lPTP9MhsV6W6u0sEkfw4BRdY83jbAKVx5wckys2FaFlRrknKoInvdD9vAvM45lc4rleWYH23+8xpQ0L94NffAgH2VzGj9EvPyBKioUYzSXjj/fHtfCmTlZdOZ0/jf+A7OvKPr0SqTmHw/drAKZrb5lY/0JYNq1JSQYRxA96rFBCa1AqYufv4AcJY68lf1Ph7pAHjDJFUfWhswmwxL2cwEt18LObeHTCwK+9bLELMSZzt1dEazLhZWctbQ+W9pTegp5va+rimgL/v4hLhrAuCpcP1up+LTkcoY9nQjDiOuS7IZrprswIDAQAB";
        String hzphPrivateKey = hzphKeyPair.get("privateKey");

        // 平台方的密钥对 -- 对接时平台方提供给恒普公钥publickKey
        Map<String, String> keyPair = generateKeyPair();
        //String publickKey = keyPair.get("publicKey");
        String publickKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAinaifUFzZLO/QXsCllEnyfjPY/M6rFBByVYzBx4Qt3Fjr910+FU5HBKJ85j6TSQlVTB5D5r5Gjb9dx88xNfyDxp4ZwC7Vx9X2SM08IV92OpmoKee02DVogBbY/yNSNcf6jOK8QhDWTkoOqbX689b6PcYAe72SaRbhcEjECB0tR3fZi5aKQCx61YIgf3BHU8sz2fV4ozKxtZNGy7IKshoUZn3zrtjit5wC3onLtdKDiQqZWnHfBm9BPXaQlHh5lFXJaMkED/Avs4X8SaVTHRFzS/yh+DjoTbiGcCMLqaMgfkfx5OS7Sxq16IkuNI9mgtWAoEAMy1INsyuOeEQ4uvi6wIDAQAB";
        //String privateKey = keyPair.get("privateKey");
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKdqJ9QXNks79BewKWUSfJ+M9j8zqsUEHJVjMHHhC3cWOv3XT4VTkcEonzmPpNJCVVMHkPmvkaNv13HzzE1/IPGnhnALtXH1fZIzTwhX3Y6magp57TYNWiAFtj/I1I1x/qM4rxCENZOSg6ptfrz1vo9xgB7vZJpFuFwSMQIHS1Hd9mLlopALHrVgiB/cEdTyzPZ9XijMrG1k0bLsgqyGhRmffOu2OK3nALeicu10oOJCplacd8Gb0E9dpCUeHmUVcloyQQP8C+zhfxJpVMdEXNL/KH4OOhNuIZwIwupoyB+R/Hk5LtLGrXoiS40j2aC1YCgQAzLUg2zK454RDi6+LrAgMBAAECggEAQABoSUd4pEKllAnc/0hSAPy+ODAUndOXJQ5wFcki5vhFoOPXjnYuKLi1BWFfyUp86yy1uK3kmOZ0MJzCxSYgIDUXFF11Ouck/xxT+x1pN1vZxkbUllY7SV+q3sO9Nr8hZGsgvRsZrmNKIho+0iQOEtgsQ7CGLTIIkAHfSDQIXjLVRruiLnMY0+Ph6o45uD8Ai6qn3rgxOIsfiznCJm3PJDaoUZwaqZNtpI0zXPeYbgmyNUhKM+i/SVd7Raf/O4kkMB+uLCnfwhgV6+U2vqYBS/ot3UifHNmU24zbF1gME5D8fxHIUIl86vJ87mciJLGabiieiVgTT0SohiRGtAxZAQKBgQD2iRM8e73V4zRrk9MPV0BHLZOshN+8EQOz9B3E5mOmB3o1xj2s8cIWtRJGZrAlig/Lcg6CxUewV7ZU6xqBsoav8RmA5V/3joQVdX6P2R/86nzN1wE0VQFDPIHD8VTgtcWE4Uuhmcw0KSY9FFpHM2SS0rCey+jQNvEgwvnRxwuggQKBgQCPx3BXys+uZcbrsk7/KX4cewmziIVhqDScH9R8J3vXebql6kR3HCXlgtU7akx3abQdPu+tXfx0NxUktGR8saNCA10S5ZPFicR4Vofk94B3R9OSA9TFfEnjVwYOFWvw6Vs2o4jGIYa4VHzv1zTmm5+HC2HWkvHH1vS/BYmc7UlNawKBgBwcm03tSkNGaibOL2XzbAfxwYINkLmQMxzk/DQElV5MQfo5xbSfyKLwOMpAUVumd5CaS99LgZYxGkBqopYhdkw/sMnK3fuLi7IquKjMeGJNlreeCawe+UribmWacjRBZel2Czq9H9Te0FZsqLKvwIshbTReSgjcCvjSYswx5ziBAoGAC3BUkceC6l8SimHvQwGsFNv9E5mC8xgMx3cI0q/ZfHUk0lpnBArrQO09xbJZTw7L05/UNbjKgvx25Cqt6/W+BVx7JSu6q/af+Or5eM/wGo1OWcmQPf37aujbhHdT1SDbrucn1IuKSlP+dJ5siQRMltPi/s1GCx4g7qmkHyYJtO0CgYEAwByY38u1jUeEk+4CGM+DNAIjtzq3O+QdwAoIti7OuB2PeVr2RD9eSKjCMTw7Mq0R6WLLFKjsUGAMexwvbMMlFwKHqhHpsmFD6Fn+pNfa/RJXLTAcir5DQt5jjXjS4pvIdwcztZmSU7ZZbN78Ydj7VPkZDwEHVeu0h3eS4iOZTXk=";

        // 接口调用示例
        Map<String, Map> map = new HashMap<>();
        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("RequestId", "HPJK2021040610000028");
        map.put("request", requestMap);
        String jsonData = JSONObject.toJSONString(map);
        System.out.println("request json:" + jsonData);
        // 使用恒普公钥加密报文
        String reqData = encrypt(hzphPublickKey, jsonData);
        // 使用平台方秘钥签名
        String reqSign = sign(reqData.getBytes(), privateKey);
        // 请求报文
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("merchantId", "trafree");
        hashMap.put("methodId", "OpenAcctQuery");
        hashMap.put("reqData", reqData);
        hashMap.put("sign", reqSign);

        // 调用POST请求 。。。
        JSONObject requestObject = new JSONObject(hashMap);
        System.out.println("requestObject is :" + requestObject);
        boolean verifyReqResult = RSAUtil.verify(reqData.getBytes(), publickKey, reqSign);
        String reqStr = "{\\\"reqData\\\":\\\"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\\\",\\\"merchantId\\\":\\\"trafree\\\",\\\"sign\\\":\\\"eoz7hFmn5I/hViDUwUYGcoFAqWrWjD3o361u5bDwlqmzTPSxwTpyTb3tPExxnOAlaXPte8iZs4LowZUgTLx1s5tWFBcUfhYjxY3dPWgAPjdm5h/iOePyJv/D6YF7exEX+Ci0IHRwT+ZLa3vrlcZnpLilZm301UEplPRUAjAHtcJxSEysx/ftq+2CClw9NzHBwjQp4dV2BDOVnkyHrf2jChKF5dTz7kSiMdU9axKvgMrTZgKKL9uIjSluPQXT6iPvbsTzLcxq5Ob8x1FZ2lKZc3+HS4V1boSpHQkylphgINWefJvbndU+EspM4z8dmjZZbtwOXGpUvLGJ0nvqWjlKjQ==\\\",\\\"methodId\\\":\\\"AccessCreditInfoApply\\\"}";
        System.out.println(requestObject.toJSONString());
        String res = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://corporate-loan-qa.hzphfin.com/api");
        StringEntity stringEntity = new StringEntity(requestObject.toString());
        //StringEntity stringEntity = new StringEntity(reqStr);
        stringEntity.setContentEncoding("utf-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            res = EntityUtils.toString(httpResponse.getEntity());
        }

        System.out.println("res-------->" + res);
        // 响应报文解析
        JSONObject responseObject = JSONObject.parseObject(res);
        String respData = responseObject.getString("respData");
        String respSign = responseObject.getString("sign");
        // 结果验签
        boolean result = verify(respData.getBytes(), hzphPublickKey, respSign);
        if (result) {
            // 解密报文 -- 是个json字符串
            String decrypt = decrypt(privateKey, respData);
            System.out.println(decrypt);
            JSONObject resp = JSONObject.parseObject(decrypt);
            if ("000000".equals(resp.getString("code"))) {
                // 接口调用成功
                resp.getString("msg");
                Map respResult = resp.getObject("result", Map.class);
            }
        }

    }

}

