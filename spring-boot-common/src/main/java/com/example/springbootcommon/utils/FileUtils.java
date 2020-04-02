package com.example.springbootcommon.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FileUtils
 * @Description TODO  import org.apache.commons.io.IOUtils;
 * @Author Leo
 * @Date 2020/4/2 16:54
 * @Version 1.0
 */
public class FileUtils {
    /**
     * 工具类编写范例，使用重载编写不同参数类型的函数组
     * 需求：
     * 输入是一个utf-8格式的文件的文件名，把里面内容输出到一个list
     */

    private static final String DEFAULT_CHARSET = "UTF-8";


    public static List<String> readFile2List(String fileName) throws IOException {
        return readFile2List(fileName, DEFAULT_CHARSET);
    }

    public static List<String> readFile2List(String fileName, String charset) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        return readFile2List(fileInputStream, charset);
    }

    public static List<String> readFile2List(File file) throws IOException {
        return readFile2List(file, DEFAULT_CHARSET);
    }

    public static List<String> readFile2List(File file, String charset) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return readFile2List(fileInputStream, charset);
    }

    public static List<String> readFile2List(InputStream inputStream) throws IOException {
        return readFile2List(inputStream, DEFAULT_CHARSET);
    }

    /**
     * 主体代码
     * @param inputStream
     * @param charset
     * @return
     * @throws IOException
     */
    public static List<String> readFile2List(InputStream inputStream, String charset) throws IOException {
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                list.add(s);
            }
        } finally {
            IOUtils.closeQuietly(bufferedReader);
        }
        return list;
    }

}
