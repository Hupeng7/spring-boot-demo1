package com.demo.springbootdemoeasyexcel;

import com.alibaba.excel.EasyExcel;
import com.demo.springbootdemoeasyexcel.model.DemoData;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Test1
 * @Description
 * @Author H
 * @Date 2021/2/23 15:26
 * @Version 1.0
 */

@SpringBootTest
public class Test1 {

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead(){
        String fileName = "D:\\test\\easyExcel\\"+ File.separator+"demo.xlsx";
        //EasyExcel.read(fileName, DemoData.class,new DemoData);
    }

    @Test
    public void simpleWrite(){
        String fileName = "D:\\test\\easyExcel\\" + "write" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName,DemoData.class).sheet("模板").doWrite(data());

    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.567);
            list.add(data);
        }
        return list;
    }


}
