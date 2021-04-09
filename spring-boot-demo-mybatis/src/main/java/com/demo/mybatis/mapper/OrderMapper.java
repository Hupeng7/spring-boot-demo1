package com.demo.mybatis.mapper;

import com.demo.mybatis.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    @Insert("insert into order (t0_due_principal,t0_due_interest,t0_due_fee3,t30_paid_fee3)" +
            "values(#{order.t0DuePrincipal},#{order.t0DueInterest},#{order.t0DueFee3},#{t30PaidFee3})")
    void insert(@Param("order") Order order);
}
