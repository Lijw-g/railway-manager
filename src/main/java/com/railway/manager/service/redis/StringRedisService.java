package com.railway.manager.service.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @author: Lijiwen
* Description:
* @param:  * @param null
* @return
* @createDate 2019-11-16 23:28
**/
@Service
public class StringRedisService {
    private static final Logger logger = LoggerFactory.getLogger(StringRedisService.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 缓存key
     * @param key
     * @param val
     * @param expire
     * @param timeUnit
     * @return void
     * @Date 18:13 2019-05-08
     */
    public void setString(String key, String val, Long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, val, expire, timeUnit);
    }

    /**
     * 检查key是否存在
     * @param key
     * @return java.lang.Boolean
     * @Date 18:13 2019-05-08
     */
    public Boolean getCachKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key删除缓存
     * @param key
     * @return void
     * @Date 18:16 2019-05-08
     */
    public Boolean delCach(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 根据key获取val
     * @param key
     * @return java.lang.String
     * @Date 18:25 2019-05-08
     */
    public String getVal(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}