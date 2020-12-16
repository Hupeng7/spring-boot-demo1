package com.example.springbootdemomytool.utils.testdemo;


import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 * @ClassName TestDemo02
 * @Description
 * @Author H
 * @Date 2020/12/11 18:03
 * @Version 1.0
 */
public class TestDemo02 {

    public Integer getIntVal() {
        return 10;
    }

    public static void IOUtilsDemo(){

        IOUtils.closeQuietly((InputStream) null);

    }
}
