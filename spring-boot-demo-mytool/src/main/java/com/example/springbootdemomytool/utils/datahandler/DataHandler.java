package com.example.springbootdemomytool.utils.datahandler;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName DataHandler
 * @Description 数据的写入、写出
 * 1. 第一步：数据报文整理
 * 整理数据内容报文，输出后缀为txt的文本文件。
 * 说明：文本文件中所有的数据项内容，均无需作加密处理。包括姓名，证件号码，手机号码等关键数据项信息
 * 2.  第二步：数据文件压缩  done
 * 为提高数据文件的传输效率，机构在上报数据文件前，需对txt文本文件作压缩处理，生成后缀为zip的压缩文件。
 * 注意：
 * 1.待压缩的数据文件后缀必须为txt。
 * 2.压缩文件中不能包含子目录。
 * 3.一个压缩文件中只能有一个数据文件。
 * 3. 第三步：压缩文件加密
 * 对整个zip压缩文件作字节流加密处理，生成后缀为cry的加密文件。
 * @Author Leo
 * @Date 2020/5/11 15:39
 * @Version 1.0
 */
public class DataHandler {

    public static void main(String[] args) {

        Map dataApplyInfo = new HashMap<>();
        dataApplyInfo.put("name", "张三");
        dataApplyInfo.put("pid", "31010119900101001X");
        dataApplyInfo.put("mobile", "13812345678");

        Map dataLoanApplyInfo = new HashMap<>();
        dataLoanApplyInfo.put("name", "李四");
        dataLoanApplyInfo.put("pid", "31010119920101001X");
        dataLoanApplyInfo.put("mobile", "13812345699");

        final String priKey = "G0ZFuqfGnGUSTz33iybFJcweISmb4W5nx4gonrO3OjvbfqOsoISUuiqtbw7NucOxeo5KRwm2Mzr1nlmgSP/bgP2cmlfF4CRqGgOuEhfCddvxUpwReFJDcb/VXKmi+jbt2M3g5WvAGxpFwer7XJWhXXSSYSUw+XKeM9t4M1reqag=";
        // 单向光闸写入文件线程
        new Thread(new Runnable() {
            // 将map转为json
            JSONObject applyInfoJson = new JSONObject(dataApplyInfo);
            JSONObject loanApplyInfoJson = new JSONObject(dataLoanApplyInfo);

            @Override
            public void run() {
                try {
                    //创建文件夹及文件
//                    String fileName = UUID.randomUUID().toString().replaceAll("-", "");
                    Date now = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmsss");
                    String fileName = simpleDateFormat.format(now);

                    String wfileName = "d:\\test\\" + fileName + ".obd";
                    String path = "d:\\test\\" + fileName + ".txt";
                    File fpath = new File(path);
                    File parentFile = fpath.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    fpath.createNewFile();
                    // 将json写入到txt中
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true)));
                    StringBuffer thisLine = new StringBuffer("");
                    thisLine.append(priKey)
                            .append("\n")
                            .append("#applyInfo\n")
                            .append(applyInfoJson.toJSONString())
                            .append("\n")
                            .append(applyInfoJson.toJSONString())
                            .append("\n")
                            .append("#loanApplyInfo\n")
                            .append(loanApplyInfoJson.toJSONString());

                    // 拼接所有字段为一行数据，用tab键分隔
//                    if (i < rowData.length - 1) { // 不是最后一个元素
//                        thisLine.append(field).append("\t");
//                    } else { // 是最后一个元素
//                        thisLine.append(field);
//                    }
//
                    out.write(String.valueOf(thisLine));
                    out.close();
                    System.out.println("write success" + path);
                    //剪切并重命名
                    //fpath.renameTo(new File(wfileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}
