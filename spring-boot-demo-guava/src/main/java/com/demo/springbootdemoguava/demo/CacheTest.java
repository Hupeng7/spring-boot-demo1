package com.demo.springbootdemoguava.demo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListeners;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheTest
 * @Description TODO
 * @Author hup
 * @Date 2020/7/16 14:45
 * @Version 1.0
 */
@Slf4j
public class CacheTest {
    public static void main(String[] args) throws Exception {
        log.info("accessTime---------");
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                // 缓存回收 1 基于容量的大小 1.1maximumSize()直接设置容器大小,maximumWeight()+weigher()基于权重设置
                .maximumSize(3)  // 设置缓存最大大小
                //.maximumWeight(5)  // 配合weigher()方法的权重设置
                //.weigher((String key, String value) -> key.length()) //设置缓存权重
                // 缓存回收 2定时回收
                .expireAfterAccess(Duration.ofSeconds(3))  // 基于访问顺序的缓存
                // .expireAfterWrite(Duration.ofSeconds(3)) // 基于写入顺序的缓存
                // 支持异步刷新
                //.refreshAfterWrite(Duration.ofSeconds(3))
                // 当缓存被移除时可以通过监听获取回调，支持异步
                //.removalListener(notification -> System.out.println(notification.getKey() + "---removed"))
                .removalListener(RemovalListeners.asynchronous(notification -> System.out.println(notification.getKey() + "----asynchronous--removed"),
                        new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), r -> new Thread(Thread.currentThread().getThreadGroup(), r, "RemovalListeners.asynchronous"))))
                // 开启统计功能
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        log.info("real key...");
                        return key.toUpperCase();
                    }
                });
        // 显示插入
        cache.put("k1", "putK1");
        // 清除
        cache.invalidate("k1");
        cache.invalidateAll();
        // 支持 如果有缓存则返回；否则运算、缓存、然后返回
        log.info(cache.get("kkk", () -> "fffff"));

        // get方法必须获取ExecutionException异常
        log.info(cache.get("k1"));
        TimeUnit.SECONDS.sleep(2);

        // getUnchecked()当加载实际值时没有抛出异常声明可以用这个api
        log.info(cache.getUnchecked("k2"));
        log.info(cache.getUnchecked("k2"));
        TimeUnit.SECONDS.sleep(2);
        log.info(cache.getUnchecked("k3"));

        TimeUnit.SECONDS.sleep(3);
        log.info(cache.getUnchecked("k1"));
        log.info(cache.getUnchecked("k2"));
        log.info(cache.getUnchecked("k3"));

        // 统计信息
        log.info("recordStats : " + cache.stats());
        System.exit(0);
    }


}
