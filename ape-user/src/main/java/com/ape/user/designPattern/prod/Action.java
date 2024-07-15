package com.ape.user.designPattern.prod;

/**
 * @Author : 李良杰
 * @Description :规定模板使用
 * @Date : 2024/7/15 22:53
 * version :1.0
 **/
public interface Action {
    /**
     * 参数校验，可以自定义异常抛出
     */
    void validate();

    /**
     * 执行
     */
    void execute();

    /**
     * 后续
     */
    void after();
}
