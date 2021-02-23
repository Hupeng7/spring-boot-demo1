package com.example.springbootdemomytool.utils.collectionsdemo;

import com.example.springbootdemomytool.beans.User;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName CollectionsDemo
 * @Description
 * @Author hup
 * @Date 2020/8/25 14:24
 * @Version 1.0
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        // 明确键类型的Map
        BigDecimal bigDecimal = BigDecimal.valueOf(0.11D);

        // 判断集合是否为空
        boolean empty = CollectionUtils.isEmpty(list);

    }

    // 赋值静态成员变量正例
    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
    }

    private static List<String> list = new ArrayList<>();

    static {
        list.add("Tom");
        list.add("Jerry");
        list.add("Anthony");
    }

    // 返回空数组和空集正例
    public static User[] getUsers() {
        return new User[0];
    }

    public static List<User> getUserList() {
        return Collections.emptyList();
    }

    public static Map<String, User> getUserMap() {
        return Collections.emptyMap();
    }

}
