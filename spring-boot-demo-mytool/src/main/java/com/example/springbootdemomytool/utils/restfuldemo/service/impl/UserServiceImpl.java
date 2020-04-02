package com.example.springbootdemomytool.utils.restfuldemo.service.impl;

import com.example.springbootdemomytool.utils.restfuldemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.springbootdemomytool.utils.restfuldemo.utils.CheckUtil.check;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/1 15:34
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public boolean delete(Long id) {
        // 参数校验
        log.info("delete config,id:{}", id);
        check(id > 0L, "id.error", id);
        boolean result = true;
        log.info("delete config success,id:{},result:{}", id, result);
        return result;
    }
}
