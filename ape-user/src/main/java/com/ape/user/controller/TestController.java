package com.ape.user.controller;

import com.ape.bean.Result;
import com.ape.util.RedisShareLockUtil;
import com.ape.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/10 22:57
 * version :1.0
 **/
@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisShareLockUtil redisShareLockUtil;
    /**
     *
     * @return
     */
    @GetMapping("/test") // 修正了括号
    public String test(){
        return "test";
    }

    @GetMapping("/testRedis")
    public String testRedis() {
        redisTemplate.opsForValue().set("name", "qj");
        String name = (String) redisTemplate.opsForValue().get("name");

        return name;
    }



    @GetMapping("/testRedisUtil")
    public Result testRedisUtil() {
        redisUtil.set("name", "qj");
        return  Result.ok();
    }

    @GetMapping("/testRedisShareLock")
    public String testRedisShareLock() {
        boolean result = redisShareLockUtil.lock("qj", "123456", 10000L);
        log.info("分布式锁获取：{}", result);
        return String.valueOf(result);
    }

}