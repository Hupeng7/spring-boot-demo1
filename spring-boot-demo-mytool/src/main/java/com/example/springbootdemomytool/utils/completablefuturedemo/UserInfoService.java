package com.example.springbootdemomytool.utils.completablefuturedemo;

/**
 * @ClassName UserInfoService
 * @Description
 * @Author H
 * @Date 2021/12/15 11:55
 * @Version 1.0
 */
public class UserInfoService {
    public UserInfo getUserInfo(Long userId) throws InterruptedException {
        Thread.sleep(300);
        return new UserInfo("1", "bruce", 18);
    }

}
