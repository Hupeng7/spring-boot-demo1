package com.example.springbootdemomytool.utils.zipdemo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @ClassName ZipDemo1
 * @Description 解压zip文件 并读取
 * 重复读取，后面的文件会覆盖前面
 * @Author H
 * @Date 2021/3/3 14:42
 * @Version 1.0
 */
public class ZipDemo1 {
    public static void zipUncompress(String inputFile) throws Exception {
        File srcFile = new File(inputFile);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        String destDirPath = inputFile.replace(".zip", "");
        //创建压缩文件对象 windows环境下，默认字符集为GBK，ZipFile默认使用UTF-8字符集，当文件名存在中文时，处理时就会报错
        // https://blog.csdn.net/Asuran18/article/details/80403689
        ZipFile zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
        // ZipFile zipFile = new ZipFile(srcFile);
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                srcFile.mkdirs();
            } else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                File targetFile = new File(destDirPath + "/" + entry.getName());
                // 保证这个文件的父文件夹必须要存在
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                targetFile.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
        }
    }

    public static void readFiles(String inputFile) throws Exception {
        File srcFile = new File(inputFile);
        if (srcFile.isDirectory()) {
            File[] next = srcFile.listFiles();
            for (int i = 0; i < next.length; i++) {
                System.out.println("next 文件名称：" + next[i].getName());
                if (!next[i].isDirectory()) {
                    BufferedReader br = new BufferedReader(new FileReader(next[i]));
                    List<String> arr1 = new ArrayList<>();
                    String contentLine;
                    while ((contentLine = br.readLine()) != null) {
                        //JSONObject js = JSONObject.parseObject(contentLine);1 = {File@511} … toString()
                        System.out.println(i + " : " + contentLine);
                        arr1.add(contentLine);
                        System.out.println();
                    }
                    System.out.println(arr1);
                }

            }
        }
    }

    public static void main(String[] args) {
        try {
            String path = "D:\\temp\\加解密\\兰州账务文件示例01.zip";
            //zipUncompress(path);
            //readFiles(path.replace(".zip", ""));
            // readZipFile1(path);
            zipUncompressAndReadFile1(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param inputFilePathName
     */
    public static void zipUncompressAndReadFile1(String inputFilePathName) {
        File srcFile = new File(inputFilePathName);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            //throw new Exception(srcFile.getPath() + "所指文件不存在");
            return;
        }
        String destDirPath = inputFilePathName.replace(".zip", "");
        //创建压缩文件对象 windows环境下，默认字符集为GBK，ZipFile默认使用UTF-8字符集，当文件名存在中文时，处理时就会报错
        // https://blog.csdn.net/Asuran18/article/details/80403689
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                srcFile.mkdirs();
            } else {
                // 如果是文件，直接读取到内存 可放到一个数组里
                if (entry.getName().contains("ZYAS_Compensatory")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry);
                         InputStreamReader streamReader = new InputStreamReader(inputStream);
                         BufferedReader bufferedReader = new BufferedReader(streamReader);) {
                        List<String> arr = new ArrayList<>();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println("read file: " + line);
                            arr.add(line);
                        }
                        System.out.println("读出文件为: " + arr);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("今天没有代偿文件," + new Date());
                }
            }
        }
    }

    public static void zipUncompressAndReadFile(String inputFile) throws Exception {
        File srcFile = new File(inputFile);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            //throw new Exception(srcFile.getPath() + "所指文件不存在");
            return;
        }
        String destDirPath = inputFile.replace(".zip", "");
        //创建压缩文件对象 windows环境下，默认字符集为GBK，ZipFile默认使用UTF-8字符集，当文件名存在中文时，处理时就会报错
        // https://blog.csdn.net/Asuran18/article/details/80403689
        ZipFile zipFile = zipFile = new ZipFile(srcFile, Charset.forName("GBK"));

        // ZipFile zipFile = new ZipFile(srcFile);
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                srcFile.mkdirs();
            } else {
                // 如果是文件，直接读取到内存 可放到一个数组里
                if (entry.getName().contains("ZYAS_Compensatory")) {
                    InputStream inputStream = zipFile.getInputStream(entry);

                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(streamReader);

                    List<String> arr = new ArrayList<>();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println("read file: " + line);
                        arr.add(line);
                    }
                    System.out.println("读出文件为: " + arr);
                    bufferedReader.close();
                    streamReader.close();

                } else {
                    System.out.println("今天没有代偿文件," + new Date());
                }
            }
        }

    }

    public static void readZipFile(String fileBase) throws Exception {
        ZipFile zf = new ZipFile(fileBase, Charset.forName("GBK"));
        InputStream in = new BufferedInputStream(new FileInputStream(fileBase));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (!ze.isDirectory()) {

            } else {
                System.out.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
                long size = ze.getSize();
                if (size > 0) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();
                }
                System.out.println("");
            }
        }
        zin.closeEntry();
    }


}
