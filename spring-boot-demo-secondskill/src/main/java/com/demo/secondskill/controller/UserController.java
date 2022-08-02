package com.demo.secondskill.controller;

import com.demo.secondskill.component.JedisCom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


/**
 * @ClassName UserController
 * @Description
 * @Author H
 * @Date 2022/2/21 14:13
 * @Version 1.0
 */
@RestController
@Slf4j
public class UserController {

    @Value("$server.port")
    private String port;

    @Autowired
    JedisCom jedisCom;

    @GetMapping("/setnx/{key}/{val}")
    public boolean setnx(@PathVariable String key, @PathVariable String val) {
        return jedisCom.setnx(key, val);
    }

    @GetMapping("/delnx/{key}/{val}")
    public int delnx(@PathVariable String key, @PathVariable String val) {
        return jedisCom.delnx(key, val);
    }

    // 总库存
    private long nKuCuen = 0;
    // 商品key名字
    private String shangpinKey = "computer_key";
    // 获取锁的超时时间 秒
    private int timeout = 30 * 1000;

    /**
     * 秒杀抢单业务逻辑
     *
     * @return
     */
    @GetMapping("/qiangdan")
    public List<String> qiandan() {
        // 抢到商品的用户
        List<String> shopUsers = new ArrayList<>();

        // 构造用户
        List<String> users = new ArrayList<>();
//        IntStream.range(0, 1000).parallel().forEach(b -> {
//            users.add("用户-" + b);
//        });
        for (int i = 0; i < 100000; i++) {
            users.add("用户-" + i);
        }

        // 初始化库存
        nKuCuen = 5;

        Long startTime = System.currentTimeMillis();
        // 模拟开抢
        users.parallelStream().forEach(b -> {
            String shopUser = qiang(b);
            if (!StringUtils.isEmpty(shopUser)) {
                shopUsers.add(shopUser);
            }
        });
        log.info("总耗时：{}", (System.currentTimeMillis() - startTime));
        return shopUsers;
    }

    private String qiang(String b) {
        // 用户开抢时间
        long startTime = System.currentTimeMillis();

        // 未抢到的情况下，30秒内继续获取锁
        while ((startTime + timeout) >= System.currentTimeMillis()) {
            // 商品是否剩余
            if (nKuCuen <= 0) {
                break;
            }

            if (jedisCom.setnx(shangpinKey, b)) {
                // 模拟生成订单耗时操作，方便查看： 客户 多次获取锁记录
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                // 用户b拿到锁
                log.info("用户{}拿到锁--->", b);
                try {
                    // 商品是否剩余
                    if (nKuCuen <= 0) {
                        break;
                    }

                    // 模拟生成订单耗时操作，方便查看： 客户 多次获取锁记录
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 抢购成功，商品递减，记录用户
                    nKuCuen -= 1;

                    // 抢单成功调出
                    log.info("用户{}抢单成功跳出...所剩库存：{}", b, nKuCuen);

                    return b + "抢单成功，所剩库存：" + nKuCuen;
                } finally {
                    log.info("用户{}释放锁...", b);
                    // 释放锁
                    jedisCom.delnx(shangpinKey, b);
                }
            } else {
                // 用户b没拿到锁，在超时范围内继续请求锁，不需要处理
                log.info("用户{}没拿到锁", b);
            }
        }

        return "";
    }

    @GetMapping("/list")
    public List<MoUser> getList() {
        List<MoUser> users = new ArrayList<>();

        IntStream.range(1, 10).forEach(b -> {
            users.add(new MoUser(b, "客户00" + b + ",端口：" + port));
        });
        return users;
    }

    class MoUser {
        private int id;
        private String name;

        public MoUser(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
