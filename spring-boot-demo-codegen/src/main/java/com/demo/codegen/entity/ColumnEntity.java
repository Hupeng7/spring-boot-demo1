package com.demo.codegen.entity;

import lombok.Data;

/**
 * @ClassName ColumnEntity
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 14:32
 * @Version 1.0
 */
@Data
public class ColumnEntity {

    /**
     * 列表
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 备注
     */
    private String comments;

    /**
     * 驼峰属性
     */
    private String caseAttrName;

    /**
     * 普通属性
     */
    private String lowerAttrName;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 其他信息
     */
    private String extra;

}
