package com.demo.codegen.entity;

import lombok.Data;

/**
 * @ClassName GenConfig
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 14:39
 * @Version 1.0
 */
@Data
public class GenConfig {

    /**
     * 请求参数
     */
    private TableRequest request;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 作者
     */
    private String author;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String comments;


}
