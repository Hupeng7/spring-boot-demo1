package com.demo.cache.redis;

import com.demo.cache.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/2 17:58
 * @Version 1.0
 */
@Slf4j
public class RedisTest extends SpringBootDemoCacheRedisApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void get() {
        // 测试线程安全，程序结束查看redis 中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.debug("【k1】 = {}", k1);

        //
        String key = "leo:user:1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "user1"));
        // 对应String
        User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.debug("【user】 = {}", user);
    }


}
