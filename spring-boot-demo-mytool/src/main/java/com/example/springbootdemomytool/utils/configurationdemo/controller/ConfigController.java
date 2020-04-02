package com.example.springbootdemomytool.utils.configurationdemo.controller;

import com.example.springbootdemomytool.beans.Config;
import com.example.springbootdemomytool.utils.configurationdemo.service.ConfigService;
import com.example.springbootdemomytool.utils.restfuldemo.beans.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @ClassName ConfigController
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:42
 * @Version 1.0
 */
@RequestMapping("/config")
@RestController
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/all")
    public ResultBean<Collection<Config>> getAll() {
        return new ResultBean<Collection<Config>>(configService.getAll());
    }

    @PostMapping("/add")
    public ResultBean<Long> add(Config config) {
        return new ResultBean<Long>(configService.add(config));
    }

    @PostMapping("/delete")
    public ResultBean<Boolean> delete(long id) {
        return new ResultBean<Boolean>(configService.delete(id));
    }

    @PostMapping("/update")
    public ResultBean<Boolean> update(Config config) {
        configService.update(config);
        return new ResultBean<Boolean>(true);

    }

}
