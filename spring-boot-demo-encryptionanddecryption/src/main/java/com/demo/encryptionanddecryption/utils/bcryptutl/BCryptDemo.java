package com.demo.encryptionanddecryption.utils.bcryptutl;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @ClassName BCryptDemo
 * @Description
 * @Author H
 * @Date 2025/6/11 15:59
 * @Version 1.0
 */
public class BCryptDemo {

    // 生成 bcrypt 哈希
    public static String bcryptHash(String password) {
        // 第二个参数是计算复杂度(4-31)，默认是10
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    // 验证密码
    public static boolean verifyPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    public static void main(String[] args) {
        String password = "mySecurePassword123";

        // 哈希密码
        String hashed = bcryptHash(password);
        System.out.println("BCrypt Hash: " + hashed);

        // 验证密码
        boolean isValid = verifyPassword(password, hashed);
        System.out.println("Password valid: " + isValid);

        // 错误密码验证
        boolean isWrongValid = verifyPassword("wrongPassword", hashed);
        System.out.println("Wrong password valid: " + isWrongValid);
    }

}
