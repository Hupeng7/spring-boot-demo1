package com.demo.cache.redis.service;

import com.demo.cache.redis.entity.SeckillActivityRequestVO;

/**
 * @ClassName SeckillService
 * @Description
 * @Author H
 * @Date 2020/12/15 11:20
 * @Version 1.0
 */
public interface SeckillService {
    SeckillActivityRequestVO seckillHandle(SeckillActivityRequestVO request);
}
