package com.example.springbootdemomytool.utils.rsautils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName RSADemo
 * @Description RSA加密算法
 * 1. getRSAPublicAndPrivateKey() 生成一对公钥和私钥
 * 2. 公钥加密，私钥解密
 * @Author Leo
 * @Date 2020/6/18 9:57
 * @Version 1.0
 */
public class RSADemo {
    private static final int SIZE = 1024;
    static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDoiiZ2f7PfHqUqRYOzgDa7DfN/FKMoIJg0tc4RkIhbNsIRz4wfgRtV20ARLOZ4C4bxhP6/uckdaLDOLpJQdNaQMhxjeuEpNbyBi1s/Nb5f1flBaBSfvDney/znnRycWw5CgRvmIJ+Uo0IlUJJ3I31q4km0+5pE/cWmsFXTGTAl/wIDAQAB";
    static final String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOiKJnZ/s98epSpFg7OANrsN838UoyggmDS1zhGQiFs2whHPjB+BG1XbQBEs5ngLhvGE/r+5yR1osM4uklB01pAyHGN64Sk1vIGLWz81vl/V+UFoFJ+8Od7L/OedHJxbDkKBG+Ygn5SjQiVQkncjfWriSbT7mkT9xaawVdMZMCX/AgMBAAECgYA/l1gBW9bSk0zTjIMBmXReHuiRvhEILjGxaG5b+0xutUDJIhrNmr7g8b5JdB/qgCx868PGpoXLFxASskWs3ua1rqHX7fnUhlS/mTZ4jiLk8HgHS91KHFm5Np4HHfdjyT+V48ZxeExEoE6nG3w6sZvevOzQaSNExFt8tQiWNrzLsQJBAPoccKAtwAOW8VD6bLcNDABefQug0oNtyCYmsoQ58ehHe+oXVlP9cKXqSPIz5e+P75tcN9Tcmv77bqpQlEI3+r0CQQDuA8weS/VKz77Sye2Y9Gs9qjJ+RM6nyYYHzt6Bx99RrMMrvUQj2x77Yd45PXuM6OCKRPlsglREb4ctvYzIlc1rAkBFSw3VAvfYxr/yzH7ENEQn8gW47ASGzyqNj1kUEy23Sd7F21Naj7Pe88rj94bz2S/sHh+FxNzYWfauDwcUVYgNAkEA4qIFAzgqebC0r7uMnJ7nWlEsQSU9bXCrSdYF8qehkgCh2o0eS9ICfzTiR7iuYy7/V0l0AolauctEeR5K5lgVvwJBAPXJuTTymVHA9bAS/7OWAVNluyqiJ0zxRtwingPQOqZnKWGcKf94JVtaOMwVjRndV4nctntzH4gqGUYfiVxQc3E=";

    // 1.初始化发送方密钥
    public static void getRSAPublicAndPrivateKey() {
        try {
            KeyPairGenerator keyPairGenerator = null;
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            final RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            String publicKeyStr = Base64.encodeBase64String(rsaPublicKey.getEncoded());
            String privateKeyStr = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
            System.out.println("Public Key:" + publicKeyStr);
            System.out.println("Private Key:" + privateKeyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encryptRSA(byte[] resPublicKeyBytes, String targetData) {
        // 公钥加密、私钥解密 ---- 加密
        long startTimes = System.currentTimeMillis();
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(resPublicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(targetData.getBytes());
            System.out.println("公钥加密、私钥解密 ---- 加密:" + Base64.encodeBase64String(result));
            long endEncryptTime = System.currentTimeMillis();
            System.out.println("公钥加密、私钥解密 ---- 加密1个时间(单位毫秒):" + (endEncryptTime - startTimes));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("encrypt error!!!", e);
        }
    }

    public static byte[] decryptRSA(byte[] rsaPrivateKeyBytes, byte[] encodeData) {
        // 私钥解密、公钥加密 ---- 解密
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec2 = new PKCS8EncodedKeySpec(rsaPrivateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec2);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(encodeData);
            System.out.println("公钥加密、私钥解密 ---- 解密:" + new String(result));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("decrypt error!!!", e);
        }
    }

    public static void main(String[] args) {
        String src = "hello,world!";
        byte[] result;
        try {
            byte[] resPublicKeyBytes = Base64Utils.decode(RSA_PUBLIC_KEY.getBytes());
            byte[] rsaPrivateKeyBytes = Base64Utils.decode(RSA_PRIVATE_KEY.getBytes());

            result = encryptRSA(resPublicKeyBytes, src);
            long endEncryptTime = System.currentTimeMillis();
            int decryptTimes = 200000; // 并发解密的个数
            //创建一个可重用固定线程数的线程池
            ExecutorService pool = Executors.newCachedThreadPool(); // Executors.newFixedThreadPool(1000);

            for (int i = 0; i < decryptTimes; i++) {
                pool.execute(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            decryptRSA(rsaPrivateKeyBytes, result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }));
            }

            pool.shutdown();

            while (true) {
                if (pool.isTerminated()) {
                    System.out.println("结束了！");
                    long endDecryptTime = System.currentTimeMillis();
                    long totalTimes = (endDecryptTime - endEncryptTime) / 1000;
                    System.out.println("公钥加密、私钥解密 ---- 并发：" + decryptTimes + "个解密时间(单位秒):" + totalTimes);
                    break;
                }
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

