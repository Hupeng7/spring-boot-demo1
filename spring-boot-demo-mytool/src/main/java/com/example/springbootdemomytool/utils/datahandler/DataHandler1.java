package com.example.springbootdemomytool.utils.datahandler;

import com.example.springbootdemomytool.beans.User;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName DataHandler1
 * @Description
 * @Author Leo
 * @Date 2020/5/11 16:25
 * @Version 1.0
 */
public class DataHandler1 {

    public static void main(String[] args) {
        Object[] objs = new Object[5];
        User u = new User();
        for (int i = 0; i < 5; i++) {
            u.setId(Long.valueOf(i));
            u.setAccount("dep" + i);
            objs[i] = u;
            System.out.println(u.toString());
        }
        List<Object[]> rows = new ArrayList<>();
        rows.add(objs);
        String filePath = "d:\\test\\";
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmsss");
        String fileName = simpleDateFormat.format(now);
        createTxtFile(rows, filePath, fileName);
    }

    /**
     * 生成.TXT格式文件,行数几乎无上限
     */
    public static boolean createTxtFile(List<Object[]> rows, String filePath, String fileName) {
        System.out.println(rows.get(0)[0].toString());
        // 标记文件生成是否成功
        boolean flag = true;

        try {
            // 含文件名的全路径
            String fullPath = filePath + File.separator + fileName + ".txt";

            File file = new File(fullPath);
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file = new File(fullPath);
            file.createNewFile();

            // 格式化浮点数据
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(10); // 设置最大小数位为10

            // 格式化日期数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            // 遍历输出每行
            PrintWriter pfp = new PrintWriter(file, "UTF-8"); //设置输出文件的编码为utf-8
            for (Object[] rowData : rows) {
                StringBuffer thisLine = new StringBuffer("");
                for (int i = 0; i < rowData.length; i++) {
                    Object obj = rowData[i]; // 当前字段

                    // 格式化数据
                    String field = "";
                    if (null != obj) {
                        if (obj.getClass() == String.class) { // 如果是字符串
                            field = (String) obj;
                        } else if (obj.getClass() == Double.class || obj.getClass() == Float.class) { // 如果是浮点型
                            field = formatter.format(obj); // 格式化浮点数,使浮点数不以科学计数法输出
                        } else if (obj.getClass() == Integer.class || obj.getClass() == Long.class
                                || obj.getClass() == Short.class || obj.getClass() == Byte.class) { // 如果是整形
                            field += obj;
                        } else if (obj.getClass() == Date.class) { // 如果是日期类型
                            field = sdf.format(obj);
                        }
                    } else {
                        field = " "; // null时给一个空格占位
                    }

                    // 拼接所有字段为一行数据，用tab键分隔
                    if (i < rowData.length - 1) { // 不是最后一个元素
                        thisLine.append(field).append("\t");
                    } else { // 是最后一个元素
                        thisLine.append(field);
                    }
                }
                pfp.print(thisLine.toString() + "\n");
            }
            pfp.close();

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 生成.csv格式文件,行数几乎无上限
     */
    public static boolean createCsvFile(List<Object[]> rows, String filePath, String fileName) {
        // 标记文件生成是否成功
        boolean flag = true;

        // 文件输出流
        BufferedWriter fileOutputStream = null;

        try {
            // 含文件名的全路径
            String fullPath = filePath + File.separator + fileName + ".csv";

            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file = new File(fullPath);
            file.createNewFile();

            // 格式化浮点数据
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(10); // 设置最大小数位为10

            // 格式化日期数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            // 实例化文件输出流
            fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"), 1024);

            // 遍历输出每行
            Iterator<Object[]> ite = rows.iterator();
            while (ite.hasNext()) {
                Object[] rowData = (Object[]) ite.next();
                for (int i = 0; i < rowData.length; i++) {
                    Object obj = rowData[i]; // 当前字段
                    // 格式化数据
                    String field = "";
                    if (null != obj) {
                        if (obj.getClass() == String.class) { // 如果是字符串
                            field = (String) obj;
                        } else if (obj.getClass() == Double.class || obj.getClass() == Float.class) { // 如果是浮点型
                            field = formatter.format(obj); // 格式化浮点数,使浮点数不以科学计数法输出
                        } else if (obj.getClass() == Integer.class || obj.getClass() == Long.class
                                || obj.getClass() == Short.class || obj.getClass() == Byte.class) { // 如果是整形
                            field += obj;
                        } else if (obj.getClass() == Date.class) { // 如果是日期类型
                            field = sdf.format(obj);
                        }
                    } else {
                        field = " "; // null时给一个空格占位
                    }
                    // 拼接所有字段为一行数据
                    if (i < rowData.length - 1) { // 不是最后一个元素
                        fileOutputStream.write("\"" + field + "\"" + ",");
                    } else { // 是最后一个元素
                        fileOutputStream.write("\"" + field + "\"");
                    }
                }
                // 创建一个新行
                if (ite.hasNext()) {
                    fileOutputStream.newLine();
                }
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 生成.xls格式文件,单页上限： 03版是65536行 ,07版的是1048576行, 10版不知
     */
//    public static boolean createXlsFile(List<Object[]> rows, String filePath, String fileName) {
//        // 标记文件生成是否成功
//        boolean flag = true;
//
//        try {
//            // 创建一个webbook，对应一个Excel文件
//            XSSFWorkbook wb = new XSSFWorkbook();
//
//            // 在webbook中添加一个sheet,对应Excel文件中的sheet
//            XSSFSheet sheet = wb.createSheet(fileName);
//
//            // 遍历输出每行
//            for (int i = 0; i < rows.size(); i++) {
//                Object[] rowData = rows.get(i); // 每一行的数据
//                XSSFRow row = sheet.createRow(i);
//                for (int j = 0; j < rowData.length; j++) {
//                    XSSFCell cell = row.createCell(j);
//                    // 假设只有三种类型的数据
//                    if (rowData[j].getClass() == String.class) { // String类型数值
//                        cell.setCellValue((String) rowData[j]);
//                    } else if (rowData[j].getClass() == double.class) { // double类型数值
//                        cell.setCellValue((Double) rowData[j]);
//                    } else if (rowData[j].getClass() == int.class) { // int类型数值
//                        cell.setCellValue((Integer) rowData[j]);
//                    }
//                }
//            }
//
//            String fullPath = filePath + File.separator + fileName + ".xls";// 含文件名的全路径
//            File file = new File(fullPath);
//            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
//                file.getParentFile().mkdirs();
//            }
//            if (file.exists()) { // 如果已存在,删除旧文件
//                file.delete();
//            }
//            file = new File(fullPath);
//            file.createNewFile();
//            FileOutputStream fileOut = new FileOutputStream(file); // 写出数据到文件
//            wb.write(fileOut);
//            fileOut.close();
//        } catch (Exception e) {
//            flag = false;
//            e.printStackTrace();
//        }
//
//        return flag;
//    }
}
