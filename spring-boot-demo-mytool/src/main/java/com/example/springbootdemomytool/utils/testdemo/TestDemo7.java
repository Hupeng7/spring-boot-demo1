package com.example.springbootdemomytool.utils.testdemo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName TestDemo7
 * @Description
 * @Author H
 * @Date 2021/6/24 9:59
 * @Version 1.0
 */
@Slf4j
public class TestDemo7 {

    public static void main(String[] args) {
        TestDemo7 testDemo7 = new TestDemo7();
        testDemo7.closeResourceInFinally();
        testDemo7.automaticallyCloseResource();


    }

    public void closeResourceInFinally() {
        FileInputStream inputStream = null;
        try {
            File file = new File("D://temp/test.txt");
            inputStream = new FileInputStream(file);
            // use the inputStream to read a file
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        }
    }

    public void automaticallyCloseResource() {
        File file = new File("D://temp/test.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            // use the inputStream to read a file
            new Long("xyz");
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
    }


}
