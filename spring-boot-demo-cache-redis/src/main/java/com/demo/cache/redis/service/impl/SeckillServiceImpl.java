package com.demo.cache.redis.service.impl;

import com.demo.cache.redis.entity.SeckillActivityRequestVO;
import com.demo.cache.redis.service.SeckillService;

/**
 * @ClassName SeckillServiceImpl
 * @Description
 * @Author H
 * @Date 2020/12/15 11:24
 * @Version 1.0
 */
public class SeckillServiceImpl implements SeckillService {
    @Override
    public SeckillActivityRequestVO seckillHandle(SeckillActivityRequestVO request) {
        SeckillActivityRequestVO response;
        String key = "key:" + request.getSeckillId();

        //Boolean lockFlag =

        return null;
    }
}
