package com.demo.cache.redis.controller;

import com.demo.cache.redis.entity.UserEntity;
import com.demo.cache.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName RedisController
 * @Description
 * @Author H
 * @Date 2021/4/23 14:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    // redis中存储的过期时间 60s
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "set", method = RequestMethod.GET)
    public boolean redisSet(String key, String value) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("zhangsan");
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());
        //
        return redisUtil.set(key, userEntity, ExpireTime);
        //return redisUtil.set(key, value);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping(value = "expire", method = RequestMethod.GET)
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }

    @RequestMapping(value = "incr", method = RequestMethod.GET)
    public long incr(String key) {
        return redisUtil.incr(key, 1);
    }

    @RequestMapping(value = "hset", method = RequestMethod.GET)
    public boolean hset(String key) {
        boolean hset = redisUtil.hset(key, "hello", "v2222");
        return hset;
    }


    @RequestMapping(value = "hget", method = RequestMethod.GET)
    public Object hget(String key, String item) {
        Object hget = redisUtil.hget(key, item);
        return hget;
    }

    @RequestMapping(value = "hmset", method = RequestMethod.GET)
    public boolean hmset(String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("a1", "v1");
        map.put("a2", "v2");
        boolean hset = redisUtil.hmset(key, map, 60L);
        return hset;
    }


    @RequestMapping(value = "hmget", method = RequestMethod.GET)
    public Object hmget(String key) {
        Map<Object, Object> hmget = redisUtil.hmget(key);
        return hmget;
    }

    @RequestMapping(value = "zadd", method = RequestMethod.GET)
    public boolean zadd(String key, String value, String scoreStr) {
        Double score = Double.valueOf(scoreStr);
        Object obj = (Object) value;
        boolean b = redisUtil.zAdd(key, obj, score);
        return b;
    }

    @RequestMapping(value = "zrange1", method = RequestMethod.GET)
    public List<String> zrange1(String key,long start,long end){
        List<String> zrange = redisUtil.zrange(key, start, end, String.class);
        return zrange;
    }

    @RequestMapping(value = "zrange", method = RequestMethod.GET)
    public Set<Object> zrange(String key) {
        Set<Object> objects = redisUtil.zRange(key);
        return objects;
    }



}
