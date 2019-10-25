package com.demo.multi.datasource.jpa.repository.second;

import com.demo.multi.datasource.jpa.entity.second.SecondMultiTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SecondMultiTableRepository
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/24 15:16
 * @Version 1.0
 */
@Repository
public interface SecondMultiTableRepository extends JpaRepository<SecondMultiTable, Long> {
}
