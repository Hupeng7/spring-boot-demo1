package com.example.springbootdemomytool.utils.codedemo;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemomytool.beans.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName CleanCodeDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/19 11:30
 * @Version 1.0
 */
@Slf4j
public class CleanCodeDemo {
    public static void main(String[] args) {
        System.out.println("1.1 利用三元表达式");
        test1();
        test1P();

        System.out.println("1.2.利用 for-each 语句");

        System.out.println("1.3.利用 try-with-resource 语句\n" +
                "所有实现 Closeable 接口的“资源”，均可采用 try-with-resource 进行简化。");

        System.out.println("1.4.利用 return 关键字\n" +
                "利用 return 关键字，可以提前函数返回，避免定义中间变量。");

        System.out.println("1.5.利用 static 关键字\n" +
                "利用 static 关键字，可以把字段变成静态字段，也可以把函数变为静态函数，调用时就无需初始化类对象。");
        Test4 t4 = new Test4();
        double distance = t4.distance(1, 2, 3, 4);
        double distance1 = Test4P.distance(1, 2, 3, 4);

        System.out.println("1.6.利用 lambda 表达式\n" +
                "Java 8 发布以后，lambda 表达式大量替代匿名内部类的使用，在简化了代码的同时，更突出了原有匿名内部类中真正有用的那部分代码。");

        System.out.println("1.7.利用方法引用\n" +
                "方法引用（::），可以简化 lambda 表达式，省略变量声明和函数调用。");

        System.out.println("1.8.利用静态导入\n" +
                "静态导入（import static），当程序中大量使用同一静态常量和函数时，可以简化静态常量和函数的引用。\n" +
                "注意：静态引入容易造成代码阅读困难，所以在实际项目中应该警慎使用。");

        System.out.println("1.9.利用 unchecked 异常\n" +
                "Java 的异常分为两类：Checked 异常和 Unchecked 异常。Unchecked 异常继承了RuntimeException ，" +
                "特点是代码不需要处理它们也能通过编译，所以它们称作  Unchecked 异常。利用 Unchecked 异常，" +
                "可以避免不必要的 try-catch 和 throws 异常处理。");

        System.out.println("2.1.利用 Lombok 注解\n" +
                "Lombok 提供了一组有用的注解，可以用来消除Java类中的大量样板代码。");

        System.out.println("2.2.利用 Validation 注解");

        System.out.println("2.4.利用注解特性\n" +
                "注解有以下特性可用于精简注解声明：\n" +
                "1、当注解属性值跟默认值一致时，可以删除该属性赋值；\n" +
                "2、当注解只有value属性时，可以去掉value进行简写；\n" +
                "3、当注解属性组合等于另一个特定注解时，直接采用该特定注解。");

        System.out.println("5.4.简化数据拷贝");
        test54();

        System.out.println("5.5.简化异常断言");
        test55();

        System.out.println("5.6.简化测试用例\n" +
                "把测试用例数据以 JSON 格式存入文件中，通过 JSON 的 parseObject 和 parseArray 方法解析成对象。虽然执行效率上有所下降，但可以减少大量的赋值语句，从而精简了测试代码。");

        System.out.println("5.7.简化算法实现\n" +
                "一些常规算法，已有现成的工具方法，我们就没有必要自己实现了。");


    }

    private static void test1() {
        String title;
        String phone = "";
        if (isMember(phone)) {
            title = "会员";
        } else {
            title = "游客";
        }
        System.out.println("【test1】 title: " + title);
    }

    private static void test1P() {
        String phone = "";
        String title = isMember(phone) ? "会员" : "游客";
        System.out.println("【test1P】 title:" + title);
    }

    private static boolean isMember(String phone) {
        return true;
    }

    private static void test2() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("cities.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                //   TODO
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取文件异常", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("关闭文件异常", e);
                }
            }
        }
    }

    private static void test2P() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cities.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // TODO
            }
        } catch (IOException e) {
            log.error("读取文件异常", e);
        }
    }

    private static boolean test3(@NotNull List<User> users) {
        boolean hasSuper = false;
        for (User u : users) {
            if (u.getId() == 1) {
                hasSuper = true;
                break;
            }
        }
        return hasSuper;
    }

    private static boolean test3P(@NotNull List<User> users) {
        for (User u :
                users) {
            if (u.getId() == 1) {
                return true;
            }
        }
        return false;
    }

    private static final class Test4 {
        public double distance(double lng1, double lat1, double lng2, double lat2) {
            // 方法实现代码
            return 0;
        }
    }

    private static final class Test4P {
        public static double distance(double lng1, double lat1, double lng2, double lat2) {
            return 1;
        }
    }

    private static void test16() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO
            }
        }).start();

        // 精简
        new Thread(() -> {
            // TODO
        }).start();
    }

    private static void test17() {
        List<User> nameArray = new ArrayList();
        // Arrays.sort(nameArray,(a,b) ->a.compareToIgnoreCase(b));
        List<Long> userList = nameArray.stream()
                .map(user -> user.getId())
                .collect(Collectors.toList());

        // 精简
        //Arrays.sort(nameArray, String::compareToIgnoreCase);
        List<Long> userIdList1 = nameArray.stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

    private static void test22() {
    }

    @Getter
    @Setter
    @ToString
    class UserCreateVO {
        @NotBlank(message = "user name cannot null")
        private String name;
        @NotNull(message = "公司标识不能为空")
        private String companyId;
    }

    @Validated
    public Long createUser(@Valid UserCreateVO userCreateVO) {
        System.out.println(userCreateVO);
        return 1L;
    }

    private static void test23() {
        System.out.println("2.3.利用 @NonNull 注解\n" +
                "Spring 的 @NonNull 注解，用于标注参数或返回值非空，适用于项目内部团队协作。只要实现方和调用方遵循规范，" +
                "可以避免不必要的空值判断，这充分体现了阿里的“新六脉神剑”提倡的“因为信任，所以简单”。");
        queryCompanyUser(new String("1"));
    }

    public static @NonNull
    List<User> queryCompanyUser(@NonNull String companyId) {
        List<User> userList = new ArrayList<>();
        return userList;
    }

    public static void test24() {
//        @Lazy(true)
//        @Service(value = "userService")
//        @RequestMapping(path = "/getUser",method = RequestMethod.GET)
//        public void hello(){}

//        @Lazy
//        @Service("userService")
//        @RequestMapping("/getUser")
//        public void hello(){}
    }

    private static void test54() {
        User userDO = new User();
        userDO.setId(1L);
        userDO.setAccount("user");
        userDO.setEmail("123@hh.com");
        List<User> userList = new ArrayList<>();

        User userVO = new User();
        userVO.setId(userDO.getId());
        userVO.setAccount(userDO.getAccount());
        userVO.setEmail(userDO.getEmail());

        userList.add(userVO);

        User userVOSmart = new User();
        BeanUtils.copyProperties(userDO, userVOSmart);
        System.out.println(userVOSmart);
        userList.add(userVOSmart);
        System.out.println(userList);

        // 反例
        // 精简代码，但不能以过大的性能损失为代价。例子是浅层拷贝，用不着 JSON 这样重量级的武器。
        List<User> userVOList = JSON.parseArray(JSON.toJSONString(userList), User.class);
    }

    private static void test55() {
        String userId = null;
        // 精简
        Assert.notNull(userId, "精简-用户标识不能为空");

        // 普通
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("用户标识不能为空");
        }
    }

    private static void test56() {
        User user = new User();
        user.setAccount("zhang san");
        user.setId(2L);
        user.setEmail("666@jj.com");
        // TODO create user

        String jsonText = JSON.toJSONString("createUser.json");
        User user1 = JSON.parseObject(jsonText, User.class);
        // TODO create user1

        // 建议：JSON 文件名最好以被测试的方法命名，如果有多个版本可以用数字后缀表示。
    }

    private static final String MAX_VALUE = "1000";
    private static void test94(String value) {
        boolean isMax = (value != null && value.equals(MAX_VALUE));
//        boolean isTrue = (result != null && result.equals(Boolean.TRUE));

        boolean isMax1 = MAX_VALUE.equals(value);
    }

}
