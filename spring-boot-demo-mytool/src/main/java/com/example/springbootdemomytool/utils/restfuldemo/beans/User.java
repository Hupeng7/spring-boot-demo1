package com.example.springbootdemomytool.utils.restfuldemo.beans;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/1 14:43
 * @Version 1.0
 */
@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private int userType;

}
