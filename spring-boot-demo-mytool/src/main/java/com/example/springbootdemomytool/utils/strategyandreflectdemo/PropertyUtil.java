package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName PropertyUtil
 * @Description 获取配置文件参数工具类
 * 拥有缓存功能，当查询了某个文件时会自动缓存
 * @Author H
 * @Date 2021/9/9 17:41
 * @Version 1.0
 */
public class PropertyUtil {
    private static Map<String, Properties> cache = new HashMap<>();

    public static String get(String configFileName, String key) {
        return getProperties(configFileName).getProperty(key);
    }

    public static Properties getProperties(String configFileName) {
        if (cache.get(configFileName) != null && cache.get(configFileName).size() > 0) {
            return cache.get(configFileName);
        }

        InputStream inputStream = PropertyUtil.class.getResourceAsStream(configFileName);
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cache.put(configFileName, properties);
        return properties;
    }

}
