package com.ape.util;

import com.ape.exception.ShareLockException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author : 李良杰
 * @Description :Redis分布式抢占锁
 * @Date : 2024/7/11 15:48
 * version :1.0
 **/
@Component
public class RedisShareLockUtil {
    /**
     * Redis工具类实例，用于操作Redis。
     */
    @Resource
    private RedisUtil redisUtil;

    /**
     * 加锁尝试的超时时间，单位：毫秒。
     */
    private Long TIME_OUT = 1000L;

    /**
     * 尝试获取分布式锁。
     *
     * @param lockKey     锁的关键字。
     * @param requestId   请求的唯一标识。
     * @param time        锁的持有时间，单位：毫秒。
     * @return 如果成功获取锁，返回true；否则返回false。
     */
    public boolean lock(String lockKey, String requestId, Long time) {
        // 检查参数是否合法
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-加锁参数异常");
        }
        long currentTime = System.currentTimeMillis();
        long outTime = currentTime + TIME_OUT;
        Boolean result = false;
        while (currentTime < outTime) {
            result = redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);
            if (result) {
                return result;
            }
            try {
                // 等待一段时间后再次尝试
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime = System.currentTimeMillis();
        }
        return result;
    }

    /**
     * 释放分布式锁。
     *
     * @param key         锁的关键字。
     * @param requestId   请求的唯一标识。
     * @return 如果成功释放锁，返回true；否则返回false。
     */
    public boolean unLock(String key, String requestId) {
        // 检查参数是否合法
        if (StringUtils.isBlank(key) || StringUtils.isBlank(requestId)) {
            throw new ShareLockException("分布式锁-解锁参数异常");
        }
        try {
            String value = redisUtil.get(key);
            // 验证锁是否被当前请求持有
            if (requestId.equals(value)) {
                redisUtil.del(key);
                return true;
            }
        } catch (Exception e) {
            // 忽略释放锁时的异常
            // 补日志
        }
        return false;
    }

    /**
     * 尝试立即获取分布式锁。
     *
     * @param lockKey     锁的关键字。
     * @param requestId   请求的唯一标识。
     * @param time        锁的持有时间，单位：毫秒。
     * @return 如果成功获取锁，返回true；否则返回false。
     */
    public boolean tryLock(String lockKey, String requestId, Long time) {
        // 检查参数是否合法
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-尝试加锁参数异常");
        }
        return redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);
    }
}
