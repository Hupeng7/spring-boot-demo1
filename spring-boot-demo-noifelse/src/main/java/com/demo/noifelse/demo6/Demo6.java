package com.demo.noifelse.demo6;

import lombok.SneakyThrows;
import org.springframework.util.Assert;

/**
 * @ClassName Demo6
 * @Description
 * @Author H
 * @Date 2021/2/9 14:37
 * @Version 1.0
 */
public class Demo6 {

    public String getMessage(int code) {
        if (code == 1) {
            return "成功";
        } else if (code == -1) {
            return "失败";
        } else if (code == -2) {
            return "网络超时";
        } else if (code == -3) {
            return "参数错误";
        }
        throw new RuntimeException("code错误");
    }

    // 简单的判断
    public String getMessage2(int code) {
        if (code == 1) {
            return "成功";
        }
        return "失败";
    }

    // 三目运算
    public String getMessage3(int code) {
        return code == 1 ? "成功" : "失败";
    }

    // spring中的判断
    @SneakyThrows
    public void save(Integer code, String name) {
        if (code == null) {
            throw new Exception("code不能为空");
        } else {
            if (name == null) {
                throw new Exception("name不能为空");
            } else {
                System.out.println("doSave");
            }
        }
    }

    // 用断言Assert 简化
    public void save2(Integer code, String name) {
        Assert.notNull(code, "code not null");
        Assert.notNull(name, "name not null");
        System.out.println("doSave");
    }


}
