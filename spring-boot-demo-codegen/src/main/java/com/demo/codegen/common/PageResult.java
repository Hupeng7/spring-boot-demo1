package com.demo.codegen.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 11:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 页码
     */
    private int pageNumber;

    /**
     * 每页结果数
     */
    private int pageSize;

    /**
     * 结果集
     */
    private List<T> list;


}
