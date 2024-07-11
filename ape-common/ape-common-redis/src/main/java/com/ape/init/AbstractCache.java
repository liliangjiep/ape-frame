package com.ape.init;
import org.springframework.stereotype.Component;


/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/8 21:15
 * version :1.0
 **/

@Component
public abstract class AbstractCache {

    public abstract void initCache();

    public abstract <T> T getCache();

    public abstract void clearCache();

    public void reloadCache() {
        clearCache();
        initCache();
    }
}