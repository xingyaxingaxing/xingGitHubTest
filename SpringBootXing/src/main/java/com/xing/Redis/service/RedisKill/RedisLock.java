package com.xing.Redis.service.RedisKill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component

public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private final static Logger log = LoggerFactory.getLogger(RedisLock.class);
    /*
     * 加锁
     * key
     * value 当前时间+超时时间
     * */

    public boolean lock(String key, String value) {
        //先看能不能加锁
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //然后判断如果过期
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)
                < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    public void nulock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁失败");
        }

    }

}
