package com.example.springbootdemomytool.utils.java8;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName LambdaMapDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/7 18:08
 * @Version 1.0
 */
public class LambdaMapDemo {

    private Map<String, Object> map = new HashMap<>();

    @Before
    public void initData() {
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "c3");
        map.put("k4", 4);
        map.put("k5", 5);
        map.put("k5", 'h');
    }

    /**
     * 遍历Map的方式一
     * 通过Map.keySet遍历key和value
     */
    @Test
    public void testErgodicWayOne() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        for (String key : map.keySet()) {
            System.out.println("map.get(" + key + ") = " + map.get(key));
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + map.get(key)));
    }


    /**
     * 遍历Map的方式二
     * 通过Map.entrySet 使用Iterator遍历key value
     */
    @Test
    public void testErgodicWayTwo() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map.entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
    }

    /**
     * 遍历Map方式三
     * 通过Map.entrySet遍历key value，在大容量时推荐使用
     */
    @Test
    public void testErgodicWayThree() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
    }

    /**
     * 遍历Map方式四
     * 通过Map.values()遍历所有的value,但不能遍历key
     */
    @Test
    public void testErgodicWayFour() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        for (Object value : map.values()) {
            System.out.println("map.value = " + value);
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map.values().forEach(System.out::println);
        // 等价于
        // map.values().forEach(value -> System.out.println(value));
    }

    /**
     * 遍历Map方式五
     * 通过 k,v遍历，Java8 独有的
     */
    @Test
    public void testErgodicWayFive() {
        System.out.println("---------------------Before JAVA8 ------------------------------");

        System.out.println("---------------------JAVA8 ------------------------------");
        map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
    }


}
