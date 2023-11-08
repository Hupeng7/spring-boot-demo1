package com.demo.encryptionanddecryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoEncryptionanddecryptionApplicationTests {
    /**
     * 加密
     */
    @Test
    public void testEncrypt() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");    // 加密的算法，这个算法是默认的
        config.setPassword("mypassword");        // 加密的salt
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "root";      // 需要加密的参数值
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);  // 输出加密结果
    }


    // 将16进制字符串转换为字节数组
    private static byte[] hexToBytes(String hexStr) {
        int len = hexStr.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
                    + Character.digit(hexStr.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * 解密
     */
    @Test
    public void testDecrypt() {
        // 原编码
        String aaa = "d38d3961d5185987779d62fefebdafa691d05639bdf77ea9cf2f93dd4922714e80c7a94edc1d98e705a89c35022e5cbf067acde086b2d5bb1be691be06a11bed7add3f4dc3237c62";
        String s = new String(hexToBytes(aaa), StandardCharsets.UTF_8);
        System.out.println("原始-解码后的字符串: " + s);

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("mypassword");
        //config.setPassword("adjqiweu1iu311jk1j23");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "I21G78/GJ4Nux59Gz7zdKg==";
        //String encryptedText = s;
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

    @Test
    public void test1() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        // 加密的算法，默认
        config.setAlgorithm("PBEWithMD5AndDES");
        // 加密的 salt
        //config.setPassword("mypassword");
        config.setPassword("35579B7F9C8CB15E");
        standardPBEStringEncryptor.setConfig(config);
        //String encryptedText = "OiQ/EYfNkGf+UyV13smf/g==";
        //
        String encryptedText = "LPInZxCUlIY6yQxt5Fu710wpLeghar0raBP3J2EMp9U=";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

}
