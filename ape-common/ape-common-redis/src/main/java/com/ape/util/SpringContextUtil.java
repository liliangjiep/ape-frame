package com.ape.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/8 21:20
 * version :1.0
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationCtxt;



    public static ApplicationContext getApplicationContext() {
        return applicationCtxt;
    }

    /**
     * 设置应用程序上下文实例。
     *
     * 此方法用于初始化应用程序上下文，通常由框架在应用程序启动时调用。
     *
     * @param applicationContext 应用程序上下文实例，用于管理Bean。
     * @throws BeansException 如果设置应用程序上下文时发生错误。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationCtxt = applicationContext;
    }

    /**
     * 根据类型获取Bean实例。
     *
     * 此方法允许通过Bean的类型来获取Bean实例，这对于不需要知道Bean名称的情况下非常有用。
     *
     * @param type 需要获取的Bean的类类型。
     * @return 类型匹配的Bean实例。
     */
    public static Object getBean(Class type) {
        return applicationCtxt.getBean(type);
    }
}
