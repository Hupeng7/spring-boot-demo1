package com.example.springbootdemomytool.utils.codestatistics;

import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName CodeStatisticsUtil
 * @Description TODO
 * 参考 ：1.https://blog.csdn.net/u013967628/article/details/81070135
 * 2. 一行脚本 find . -name *.java  -exec wc -l  {} \; | awk '{s+=$1}END{print s}'
 * @Author Leo
 * @Date 2020/3/31 18:19
 * @Version 1.0
 */
public class CodeStatisticsUtil {

    public static void main(String[] args) {
//        String file = CodeStatisticsUtil.class.getResource("/").getFile();
//        String path = file.replace("target/tet-classes", "src");
        // 目标路径
        String path = "D://javapro//fund-pay//";
        ArrayList<File> files = getFile(new File(path));
        for (File f : files) {
            if (f.getName().matches(".*\\.kt")) {
                count(f);
                System.out.println(f);
            }
        }
        System.out.println("统计文件：" + files);
        System.out.println("代码行数：" + codeLines);
        System.out.println("注释行数：" + commentLines);
        System.out.println("空白行数：" + blankLines);
    }

    static long files = 0;
    static long codeLines = 0;
    static long commentLines = 0;
    static long blankLines = 0;
    static ArrayList<File> fileArrayList = new ArrayList<File>();

    private static void count(File file) {
        BufferedReader br = null;
        boolean flag = false;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim(); // 除去注释前的空格
                if (line.matches("^[ ]*$")) { // 匹配空行
                    blankLines++;
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    flag = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (flag == true) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;
                }
            }
            files++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取目录下的文件和子目录下的文件
     *
     * @param file
     * @return
     */
    private static ArrayList<File> getFile(File file) {
        File[] ff = file.listFiles();
        for (File child : ff) {
            if (child.isDirectory()) {
                getFile(child);
            } else {
                fileArrayList.add(child);
            }
        }
        return fileArrayList;
    }


}
