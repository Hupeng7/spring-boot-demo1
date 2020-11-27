package com.example.springbootdemomytool.utils.jsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Jsondemo
 * @Description json数组与List相互转化
 * @Author H
 * @Date 2020/11/20 15:43
 * @Version 1.0
 */
public class JsonDemo {
    public static void main(String[] args) {
        String jsonArray = "[{\"fullName\":\"sdfsf\",\"id\":11},{\"fullName\":\"sdfsfs\",\"id\":12},{\"fullName\":\"sdfsf\",\"id\":13},{\"fullName\":\"dsfasfs\",\"id\":14},{\"fullName\":\"dsfasfs\",\"id\":15},{\"fullName\":\"dsfasfs\",\"id\":16},{\"fullName\":\"dsfasfs\",\"id\":17},{\"fullName\":\"dsfasfs\",\"id\":18},{\"fullName\":\"dsfasfs\",\"id\":19},{\"fullName\":\"dsfasfs\",\"id\":20},{\"fullName\":\"dsfasfs\",\"id\":21}]";

        List<Person> peoples = new JsonDemo().jsonToList(jsonArray);
        System.out.println("peoples: " + peoples);
        String s = new JsonDemo().listToJson(peoples);
        System.out.println("s: " + s);
    }

    public List<Person> jsonToList(String jsonArray) {
        //json转换为list
        List<Person> list = new ArrayList<Person>();
        list = JSONObject.parseArray(jsonArray, Person.class);
        return list;
    }

    public String listToJson(List<Person> list) {
        //list转换为json
        //List<Person> list = new ArrayList<Person>();
        String str = JSON.toJSON(list).toString();
        return str;
    }
}
