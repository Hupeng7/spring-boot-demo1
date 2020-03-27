package com.example.springbootdemomytool.utils.aspectdemo.entity;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 18:01
 * @Version 1.0
 */
public class User {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date creatTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}
