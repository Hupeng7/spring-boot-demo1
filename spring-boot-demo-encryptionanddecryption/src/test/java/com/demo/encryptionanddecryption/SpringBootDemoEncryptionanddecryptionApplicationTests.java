package com.demo.encryptionanddecryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    /**
     * 解密
     */
    @Test
    public void testDecrypt() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("mypassword");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "I21G78/GJ4Nux59Gz7zdKg==";
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
