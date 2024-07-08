package com.ape.config.init;

import com.ape.config.util.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/8 22:09
 * version :1.0
 **/
@Component
@ConditionalOnProperty(name = {"init.cache.enable"}, havingValue = "true")
public class InitCache implements CommandLineRunner {
    /**
     * 项目启动时候会直接运行run中内容
     */
    @Override
    public void run(String... args) throws Exception {
        // 需要知道哪些缓存需要进行预热
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Map<String, AbstractCache> beanMap = applicationContext.getBeansOfType(AbstractCache.class);
        // 调用其init方法
        if (!beanMap.isEmpty()) {
            for (Entry<String, AbstractCache> entry : beanMap.entrySet()) {
                // 获取AbstractCache的子类(实现缓存方法的类)，并调用其initCache()方法
                AbstractCache abstractCache = (AbstractCache) SpringContextUtil.getBean(entry.getValue().getClass());
                abstractCache.initCache();
            }
        }
        System.out.println("缓存成功...");
    }
}
