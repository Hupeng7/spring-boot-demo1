package com.demo.encryptionanddecryption.utils.others;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @ClassName SpringSecurityHashing
 * @Description
 * @Author H
 * @Date 2025/6/11 16:05
 * @Version 1.0
 */
public class SpringSecurityHashing {

    public static void main(String[] args) {
        String password = "mySecurePassword123";

        // BCrypt
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
        String bcryptHash = bcryptEncoder.encode(password);
        System.out.println("BCrypt: " + bcryptHash);
        System.out.println("BCrypt matches: " + bcryptEncoder.matches(password, bcryptHash));

        // PBKDF2
        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder();
        String pbkdf2Hash = pbkdf2Encoder.encode(password);
        System.out.println("PBKDF2: " + pbkdf2Hash);
        System.out.println("PBKDF2 matches: " + pbkdf2Encoder.matches(password, pbkdf2Hash));

        // SCrypt
        SCryptPasswordEncoder scryptEncoder = new SCryptPasswordEncoder();
        String scryptHash = scryptEncoder.encode(password);
        System.out.println("SCrypt: " + scryptHash);
        System.out.println("SCrypt matches: " + scryptEncoder.matches(password, scryptHash));
    }

}
