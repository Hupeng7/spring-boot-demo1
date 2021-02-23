package com.demo.springbootdemoeasyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DemoData
 * @Description
 * @Author H
 * @Date 2021/2/22 18:00
 * @Version 1.0
 */
@Data
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    // 这里用string去接日期才能格式化
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    @NumberFormat("#.##%")
    private Double doubleData;
    // 忽略这个字段
    @ExcelIgnore
    private String ignore;

}
