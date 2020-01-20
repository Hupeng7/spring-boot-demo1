package com.demo.codegen.service;

import cn.hutool.db.Entity;
import com.demo.codegen.common.PageResult;
import com.demo.codegen.entity.GenConfig;
import com.demo.codegen.entity.TableRequest;


/**
 * @ClassName CodeGenService
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/3 17:08
 * @Version 1.0
 */
public interface CodeGenService {

    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return 代码压缩文件
     */
    byte[] generatorCode(GenConfig genConfig);

    /**
     * 分页查询表信息
     *
     * @param request 请求参数
     * @return 表名分页信息
     */
    PageResult<Entity> listTables(TableRequest request);
}
