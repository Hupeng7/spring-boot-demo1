package com.demo.orm.mybatis.mapper;

import com.demo.orm.mybatis.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AccountDAO
 * @Description
 * @Author H
 * @Date 2020/12/10 14:34
 * @Version 1.0
 */
@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
}

