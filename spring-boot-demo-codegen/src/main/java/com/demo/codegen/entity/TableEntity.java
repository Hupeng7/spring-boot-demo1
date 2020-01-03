package com.demo.codegen.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName TableEntity
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 14:40
 * @Version 1.0
 */
@Data
public class TableEntity {
    /**
     * 名称
     */
    private String tableName;

    /**
     * 备注
     */
    private String comments;

    /**
     * 主键
     */
    private ColumnEntity pk;

    /**
     * 列名
     */
    private List<ColumnEntity> columns;

    /**
     * 驼峰类型
     */
    private String caseClassName;

    /**
     * 普通类型
     */
    private String lowerClassName;

}
