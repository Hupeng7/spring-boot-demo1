package com.demo.springbootdemoeasyexcel;

import com.alibaba.excel.EasyExcel;
import com.demo.springbootdemoeasyexcel.model.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * https://juejin.cn/post/6924844622275411975
 */
@SpringBootTest
public class SpringBootDemoEasyexcelApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void yy() {
        // 写法1
        String fileName = "D:\\test\\easyExcel\\" + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 500000; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.567);
            list.add(data);
        }
        return list;
    }


}
