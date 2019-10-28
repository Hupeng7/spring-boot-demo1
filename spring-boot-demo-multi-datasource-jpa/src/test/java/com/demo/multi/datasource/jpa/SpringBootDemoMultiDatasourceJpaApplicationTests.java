package com.demo.multi.datasource.jpa;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.demo.multi.datasource.jpa.entity.primary.PrimaryMultiTable;
import com.demo.multi.datasource.jpa.entity.second.SecondMultiTable;
import com.demo.multi.datasource.jpa.repository.primary.PrimaryMultiTableRepository;
import com.demo.multi.datasource.jpa.repository.second.SecondMultiTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootDemoMultiDatasourceJpaApplicationTests {
    @Autowired
    private PrimaryMultiTableRepository primaryMultiTableRepository;

    @Autowired
    private SecondMultiTableRepository secondMultiTableRepository;

    @Autowired
    private Snowflake snowflake;

    @Test
    public void testInsert() {
        PrimaryMultiTable primaryMultiTable = new PrimaryMultiTable(snowflake.nextId(), "测试名称-1");
        primaryMultiTableRepository.save(primaryMultiTable);

        SecondMultiTable secondMultiTable = new SecondMultiTable();
        BeanUtil.copyProperties(primaryMultiTable, secondMultiTable);
        secondMultiTableRepository.save(secondMultiTable);
    }


    @Test
    public void testUpdate() {
        primaryMultiTableRepository.findAll().forEach(primaryMultiTable -> {
            primaryMultiTable.setName("修改后的" + primaryMultiTable.getName());
            primaryMultiTableRepository.save(primaryMultiTable);

            SecondMultiTable secondMultiTable = new SecondMultiTable();
            BeanUtil.copyProperties(primaryMultiTable, secondMultiTable);
            secondMultiTableRepository.save(secondMultiTable);
        });
    }

    @Test
    public void testSelect() {
        List<PrimaryMultiTable> primaryMultiTables = primaryMultiTableRepository.findAll();
        log.info("【primary】 = {}", primaryMultiTables);

        List<SecondMultiTable> secondMultiTables = secondMultiTableRepository.findAll();
        log.info("【second】 = {}", secondMultiTables);
    }

    @Test
    public void testDelete(){
//        primaryMultiTableRepository.deleteById(1187578244036169728L);
//
//        secondMultiTableRepository.deleteById(1187578081032933376L);
    }

}
