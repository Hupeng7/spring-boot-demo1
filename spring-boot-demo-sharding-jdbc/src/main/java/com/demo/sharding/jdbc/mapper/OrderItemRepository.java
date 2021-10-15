package com.demo.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sharding.jdbc.model.TOrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrderItemRepository
 * @Description
 * @Author H
 * @Date 2021/10/11 14:33
 * @Version 1.0
 */
@Mapper
public interface OrderItemRepository extends BaseMapper<TOrderItem> {
}
