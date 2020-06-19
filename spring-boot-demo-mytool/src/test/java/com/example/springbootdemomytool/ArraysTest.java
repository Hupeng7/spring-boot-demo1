package com.example.springbootdemomytool;

import com.example.springbootdemomytool.beans.Propertiest1;
import com.example.springbootdemomytool.enums.ZbankPaymentResponseCodeEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ArraysTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/20 10:33
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArraysTest {

    @Autowired
    private Propertiest1 propertiest1;



    /**
     * 无法读取properties参考
     * https://blog.csdn.net/liuchaoxuan/article/details/99896854
     */
    @Test
    public void testToList() {
        List<String> strings = Arrays.asList("XD0001", "XD0010");
        System.out.println("strings: " + strings);
        System.out.println("000:" + strings.contains("000"));
        System.out.println("XD0010:" + strings.contains("XD0010"));

        String failCodeStr = propertiest1.getFailCodeStr();

        System.out.println("failCodeStr:" + failCodeStr);
        List<String> failCodeList = Arrays.asList(failCodeStr.split(","));
        System.out.println("failCodeList:" + failCodeList);

        List<String> failCodeList1 = Arrays.asList(ZbankPaymentResponseCodeEnum.XD0001.getCode(),
                ZbankPaymentResponseCodeEnum.XD0010.getCode());
        System.out.println("failCodeList1:" + failCodeList1);
        System.out.println("XD0010:" + failCodeList1.contains("XD0010"));
        System.out.println("XD0001:" + failCodeList1.contains("XD0001"));
        System.out.println("XD00011:" + failCodeList1.contains("XD00011"));

    }


}
