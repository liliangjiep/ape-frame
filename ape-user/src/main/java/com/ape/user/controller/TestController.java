package com.ape.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/10 22:57
 * version :1.0
 **/
@RestController
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;
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

}