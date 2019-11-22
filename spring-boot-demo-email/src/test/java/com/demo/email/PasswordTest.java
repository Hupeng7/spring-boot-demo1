package com.demo.email;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName PasswordTest
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/18 18:14
 * @Version 1.0
 */
public class PasswordTest extends SpringBootDemoEmailApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testGeneratePassword() {
        // 你的邮箱密码
        String password = "rmigeehtpkjphgfg";
        // 加密后的密码(注意：配置上去的时候需要加 ENC(加密密码))
        String encryptPassword = encryptor.encrypt(password);
        String decryptPassword = encryptor.decrypt(encryptPassword);

        System.out.println("password = " + password);
        System.out.println("encryptPassword = " + encryptPassword);
        System.out.println("decryptPassword = " + decryptPassword);
    }

}
