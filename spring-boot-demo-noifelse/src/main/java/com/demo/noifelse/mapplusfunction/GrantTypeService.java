package com.demo.noifelse.mapplusfunction;

import org.springframework.stereotype.Service;

/**
 * @ClassName GrantTypeService
 * @Description
 * @Author H
 * @Date 2022/7/6 11:32
 * @Version 1.0
 */
@Service
public class GrantTypeService {

    public String redPaper(String resourceId) {
        // 红包的发放方式
        return "每周末9点发放";
    }

    public String shopping(String resourceId) {
        // 购物券的发放方式
        return "每周三9点发放";
    }

    public String QQVip(String resourceId) {
        // qq会员的发放方式
        return "每周一0点开始秒杀";
    }

}
