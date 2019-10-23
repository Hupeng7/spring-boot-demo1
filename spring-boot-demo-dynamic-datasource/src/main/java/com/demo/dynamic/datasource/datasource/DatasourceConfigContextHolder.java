package com.demo.dynamic.datasource.datasource;

/**
 * @ClassName DatasourceConfigContextHolder
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/21 18:56
 * @Version 1.0
 */
public class DatasourceConfigContextHolder {

    private static final ThreadLocal<Long> DATASOURCE_HOLDER = ThreadLocal.withInitial(() -> DatasourceHolder.DEFAULT_ID);

    /**
     * 设置默认数据源
     */
    public static void setDefaultDatasource() {
        DATASOURCE_HOLDER.remove();
        setCurrentDatasourceConfig(DatasourceHolder.DEFAULT_ID);
    }

    /**
     * 设置当前数据源配置
     *
     * @param id
     */
    public static void setCurrentDatasourceConfig(Long id) {
        DATASOURCE_HOLDER.set(id);
    }

    /**
     * 获取当前数据源配置id
     *
     * @return
     */
    public static Long getCurrentDatasourceConfig() {
        return DATASOURCE_HOLDER.get();
    }


}
