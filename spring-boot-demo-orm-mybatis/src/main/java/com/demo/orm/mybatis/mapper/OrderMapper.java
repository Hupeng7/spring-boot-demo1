package com.demo.orm.mybatis.mapper;

import com.demo.orm.mybatis.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName OrderMapper
 * @Description
 * @Author H
 * @Date 2021/4/12 11:07
 * @Version 1.0
 */
@Mapper
@Component
public interface OrderMapper {

    int saveOrder(@Param("order") Order order);

    int updateOrder(@Param("order") Order order);

    /**
     * 批量更新
     * 说明：本地数据库2000条更新两个字段   需要 35S
     *      服务器数据库1000条更新一个字段  需要 1S
     * @param orders
     * @return
     */
    int batchUpdate1(@Param("orders") List<Order> orders);

    //@Select("SELECT * FROM `spring-boot-demo`.`order` limit #{limit}")
    @Select("SELECT * FROM `fund_zbank`.`order` limit #{limit}")
    List<Order> selectOrderLimit(@Param("limit") int limit);

}
