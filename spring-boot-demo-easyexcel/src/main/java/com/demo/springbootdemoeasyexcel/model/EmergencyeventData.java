package com.demo.springbootdemoeasyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @ClassName EmergencyeventData
 * @Description
 * @Author H
 * @Date 2021/2/23 15:18
 * @Version 1.0
 */
@Data
@ContentRowHeight(10)
@HeadRowHeight(15)  // 标题长度
@ColumnWidth(15)    // 标题宽度
public class EmergencyeventData {
    @ExcelProperty("序号")
    private Integer id;

    @ExcelProperty("项目名称")
    private String projectName;

    @DateTimeFormat("yyyy年MM月dd日 HH:mm:ss")
    @ExcelProperty("录入时间")
    private String createTime;

    @DateTimeFormat("yyyy年MM月dd日 HH:mm:ss")
    @ExcelProperty("日期")
    private String detectionTime;

}
