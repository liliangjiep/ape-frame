package com.ape.config;

import com.ape.interceptor.SqlBeautyInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/6/15 23:30
 * version :1.0
 **/
@Configuration
public class MybatisConfiguration {
    // 注入Bean容器
    @Bean
    @ConditionalOnProperty(value = {"sql.beauty.show"}, havingValue = "true", matchIfMissing = true)
    public SqlBeautyInterceptor sqlBeautyInterceptor() {
        return new SqlBeautyInterceptor();
    }
}
