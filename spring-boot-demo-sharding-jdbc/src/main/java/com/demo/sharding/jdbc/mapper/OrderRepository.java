package com.demo.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sharding.jdbc.model.TOrder;
import com.demo.sharding.jdbc.model.TOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OrderRepository
 * @Description
 * @Author H
 * @Date 2021/10/11 14:35
 * @Version 1.0
 */
@Mapper
public interface OrderRepository extends BaseMapper<TOrder> {
    List<TOrderDto> selectOrderAndItemByOrderId(TOrderDto tOrder);

    List<TOrderDto> selectOrderListPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("orderId") Long orderId);

    List<TOrderDto> selectOrderListPage();
}
