package com.ape.user.designPattern.prod;

import com.ape.bean.Result;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/15 22:54
 * version :1.0
 **/
public class ApiTemplateDemo {
    public static void main(String[] args) {
        ApiTemplate apiTemplate = new ApiTemplate();
        Result result = Result.ok();
        apiTemplate.execute(result, new Action() {
            @Override
            public void validate() {
                System.out.println("开始校验");
            }

            @Override
            public void execute() {
                System.out.println("执行");
            }

            @Override
            public void after() {
                System.out.println("后续执行");
            }
        });
        System.out.println(result);
    }
}
