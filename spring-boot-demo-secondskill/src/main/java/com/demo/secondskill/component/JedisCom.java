package com.demo.secondskill.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName JedisCom
 * @Description
 * @Author H
 * @Date 2022/2/21 13:55
 * @Version 1.0
 */
@Component
@Slf4j
public class JedisCom {

    @Autowired
    JedisPool jedisPool;

    @Bean
    public JedisPool getJedisPoll() {
        return new JedisPool("127.0.0.1", 6379);
    }

    public boolean setnx(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return false;
            }
            return jedis.set(key, val, "NX", "PX", 1000 * 60).equalsIgnoreCase("ok");
        } catch (Exception e) {
            log.info("setnx error,",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public int delnx(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return 0;
            }

            // if redis.call('get','orderkey')=='1111' then return redis.call('del','orderkey') else return 0 end
            StringBuilder sbScript = new StringBuilder();
            sbScript.append("if redis.call('get','").append(key).append("')").append("=='").append(val).append("'").
                    append(" then ").
                    append("    return redis.call('del','").append(key).append("')").
                    append(" else ").
                    append("    return 0").
                    append(" end");
            return Integer.valueOf(jedis.eval(sbScript.toString()).toString());
        } catch (Exception e) {
            log.info("delnx error,",e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

}
