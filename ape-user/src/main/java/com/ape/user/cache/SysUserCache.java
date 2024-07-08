package com.ape.user.cache;

import com.ape.config.init.AbstractCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/8 21:17
 * version :1.0
 **/
@Component
public class SysUserCache extends AbstractCache {
    private static final String SYS_USER_CACHE_KEY = "SYS_USER";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void initCache() {
        // 和数据库做联动，或者和其他数据来源做联动
        redisTemplate.opsForValue().set(SYS_USER_CACHE_KEY, "qj1");
    }

    @Override
    public <T> T getCache() {
        if (!redisTemplate.hasKey(SYS_USER_CACHE_KEY).booleanValue()) {
            reloadCache();
        }
        return (T) redisTemplate.opsForValue().get(SYS_USER_CACHE_KEY);
    }

    @Override
    public void clearCache() {
        redisTemplate.delete(SYS_USER_CACHE_KEY);
    }
}
