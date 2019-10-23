package com.demo.dynamic.datasource.datasource;

import com.demo.dynamic.datasource.model.DatasourceConfig;

import javax.security.auth.login.Configuration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DatasourceConfigCache
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/23 14:57
 * @Version 1.0
 */
public enum DatasourceConfigCache {

    /**
     * 当前实例
     */
    INSTANCE;

    /**
     * 管理动态数据源列表
     */
    private static final Map<Long, DatasourceConfig> CONFIG_CACHE = new ConcurrentHashMap<>();

    /**
     * 添加数据源配置
     * @param id
     * @param config
     */
    public synchronized void addConfig(Long id, DatasourceConfig config) {
        CONFIG_CACHE.put(id, config);
    }

    /**
     * 查询数据源配置
     * @param id
     * @return
     */
    public synchronized DatasourceConfig getConfig(Long id) {
        if (CONFIG_CACHE.containsKey(id)) {
            return CONFIG_CACHE.get(id);
        }
        return null;
    }

    /**
     * 清除数据源配置
     * @param id
     */
    public synchronized void removeConfig(Long id){
        CONFIG_CACHE.remove(id);
        // 同步清楚DatasourceHolder 对应的数据源
        DatasourceHolder.INSTANCE.removeDatasource(id);
    }


}
