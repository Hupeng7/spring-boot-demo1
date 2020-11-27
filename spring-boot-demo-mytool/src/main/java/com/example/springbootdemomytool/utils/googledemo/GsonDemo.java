package com.example.springbootdemomytool.utils.googledemo;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GsonDemo
 * @Description
 * @Author H
 * @Date 2020/11/27 18:51
 * @Version 1.0
 */
public class GsonDemo {

    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("userName", "zhangsan");
        data.put("hobby", "basketball");

        // 其他格式 转为 json格式
        Gson gson = new Gson();
        String s = gson.toJson(data);
        System.out.println("s===>" + s);

    }

}
