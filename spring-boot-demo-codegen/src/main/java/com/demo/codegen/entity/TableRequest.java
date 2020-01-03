package com.demo.codegen.entity;

import lombok.Data;

/**
 * @ClassName TableRequest
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 14:46
 * @Version 1.0
 */
@Data
public class TableRequest {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * jdbc-前缀
     */
    private String prepend;

    /**
     * jdbc-url
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 表名
     */
    private String tableName;


}
