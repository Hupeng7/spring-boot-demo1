package com.example.springbootdemomytool.utils.largefile;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CreateFile
 * @Description
 * @Author H
 * @Date 2021/6/10 11:58
 * @Version 1.0
 */
public class CreateFile {
    public static void main(String[] args) {
        fileWriter("D://study//test//largeFile02.txt", 9999999);
    }

    /**
     * -- 将一个大文件拆分成若干个小文件，每个文件 100000 行
     * split -l 100000 largeFile.txt -d -a 4 smallFile_
     *
     * @param filePath
     * @param recordCount
     */
    public static void fileWriter(String filePath, int recordCount) {
        // D://study//test//largeFile01.txt
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Long startTime = System.currentTimeMillis();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < recordCount; i++) {
                // 每行需要写入的数据
                bufferedWriter.write((10000000 + i + "").toCharArray());
                bufferedWriter.newLine(); // 写入换行符
                bufferedWriter.flush();   // 刷新缓冲区
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("成功写入数据！count:" + recordCount + ",耗时:" + (endTime - startTime) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWriter1(String filePath) {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("test-%d").build(),
                (r, executor) -> {
                    if (!executor.isShutdown()) {
                        try {
                            // 主线程将会被阻塞
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        File file = new File(filePath);
/**
 *  TODO 待完成
 */
//        try (LineIterator iterator = IOUtils.lineIterator(new FileInputStream(file), "UTF-8")) {
//            while (iterator.hasNext()) {
//                String line = iterator.nextLine();
//                executorService.submit(() -> convertToDB(line));
//            }
//        }

    }


}
