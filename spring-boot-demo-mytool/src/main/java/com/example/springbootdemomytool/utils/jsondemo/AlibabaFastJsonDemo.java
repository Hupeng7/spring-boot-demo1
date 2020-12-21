package com.example.springbootdemomytool.utils.jsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemomytool.beans.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AlibabaFastJsonDemo
 * @Description since:https://www.cnblogs.com/ceshi2016/p/7381478.html
 * @Author H
 * @Date 2020/12/18 17:54
 * @Version 1.0
 */
public class AlibabaFastJsonDemo {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("a", 1);
            put("b", 2);
            put("c", 3);
        }};
        JSONObject jsonObject = mapToJsonObject(map);
        System.out.println("map to jsonObject:" + jsonObject);

        Map map1 = jsonObjectToMap(jsonObject);
        System.out.println("jsonObject to object:" + map1);

        List<String> list = new ArrayList<String>() {{
            add("a");
            add("g");
            add("z");
        }};
        JSONArray jsonArray = listToJSONArray(list);
        System.out.println("list to jsonArray:" + jsonArray);

        String userJson = "[{\"account\":\"user001\",\"id\":200}]";
        User user = (User) jsonToObject(userJson);
        System.out.println("json string to object:" + user);

    }

    // 将map转换成jsonObject
    public static JSONObject mapToJsonObject(Map itemMap) {
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));
        return itemJSONObj;
    }

    // 将jsonObj 转换成 Map
    public static Map jsonObjectToMap(JSONObject jsonObject) {
        Map<String, Object> itemMap = JSONObject.toJavaObject(jsonObject, Map.class);
        return itemMap;
    }

    // 将JSONArray转换成List
    public static JSONArray listToJSONArray(List list) {
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }

    // json转成对象
    public static Object jsonToObject(String json) {
        List<User> userList = JSON.parseArray(json, User.class);
        return userList.get(0);
    }


}
