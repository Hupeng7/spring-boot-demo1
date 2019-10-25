package com.demo.multi.datasource.jpa.repository.primary;

import com.demo.multi.datasource.jpa.entity.primary.PrimaryMultiTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Leo
 */
@Repository
public interface PrimaryMultiTableRepository extends JpaRepository<PrimaryMultiTable, Long> {
}
