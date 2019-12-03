package com.demo.orm.jpa.repository;

import com.demo.orm.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/3 17:25
 * @Version 1.0
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
