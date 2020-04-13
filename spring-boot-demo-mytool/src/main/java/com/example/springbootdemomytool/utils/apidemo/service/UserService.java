package com.example.springbootdemomytool.utils.apidemo.service;

import com.example.springbootdemomytool.beans.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/13 17:04
 * @Version 1.0
 */
@Service
public class UserService {
    public String addUser(User user) {
        // do something
//        if (user == null || user.getId() == null || user.getAccount() == null || user.getPassword() == null || user.getEmail() == null) {
//            return "对象或者对象字段不能为空";
//        }
//        if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getEmail())) {
//            return "不能输入空字符串";
//        }
//        if (user.getAccount().length() < 6 || user.getAccount().length() > 11) {
//            return "账号长度必须是6-11个字符";
//        }
//        if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
//            return "密码长度必须是6-16个字符";
//        }
//        if (!Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", user.getEmail())) {
//            return "邮箱格式不正确";
//        }
        // 参数校验完毕后这里就写上业务逻辑

        return "success";
    }
}
