package com.example.springbootdemomytool.utils.configurationdemo.service;

import com.example.springbootdemomytool.beans.Config;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName ConfigService
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:43
 * @Version 1.0
 */
@Service
public class ConfigService {
    public Collection<Config> getAll() {
        return null;
    }

    public long add(Config config) {
        return 1L;
    }

    public boolean delete(long id) {
        return true;
    }
    
    public void update(Config config) {
    }


}
