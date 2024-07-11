package com.ape.user.redislua;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/11 22:52
 * version :1.0
 **/
@Component
@Slf4j
public class CompareAndSetLua {
    @Resource
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Boolean> casScript;

    // 初始化环境
    @PostConstruct
    public void init() {
        casScript = new DefaultRedisScript<>();
        casScript.setResultType(Boolean.class);
        casScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("compareAndSet.lua")));

    }

    // 调用lua脚本
    public boolean compareAndSet(String key, Long oldValue, Long newValue) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add(key);
        Boolean result = (Boolean) redisTemplate.execute(casScript, keys, oldValue, newValue);
        return result;
    }
}
